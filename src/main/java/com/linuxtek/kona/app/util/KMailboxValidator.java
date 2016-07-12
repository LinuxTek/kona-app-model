package com.linuxtek.kona.app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.naming.CommunicationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.locale.KValidator;
import com.linuxtek.kona.util.KStringUtil;


// Basically taken and modified from rgagnon: 
// See: http://www.rgagnon.com/javadetails/java-0452.html
// License: 
//    http://www.rgagnon.com/varia/faq-e.html#license
//    There is no restriction to use individual How-To in a development (compiled/source) but a mention is appreciated. 
public class KMailboxValidator {
	private static Logger logger = LoggerFactory.getLogger(KMailboxValidator.class);


	private String fromDomain;
	private String fromEmail;

	public KMailboxValidator(String fromEmail) {
		this.fromEmail = fromEmail;
		this.fromDomain = fromEmail.substring(fromEmail.indexOf("@")+1);
	}

	public static void main(String args[]) {
		String testData[] = {
				"blisdcasdcasdc@googlemail.com",                
				"info@bilderbuch-stoff.de",
				"xxx@bilderbuch-stoff.de", // invalid
				"s.muncke@tarent.de", //invalid
				"fail.me@nowhere.spam" // Invalid domain name
		};

		KMailboxValidator validator = new KMailboxValidator(args[0]);
		for (int ctr = 0; ctr < testData.length; ctr++) {
			System.out.println(testData[ctr] + " is valid? "
					+ validator.mayMailboxExist(testData[ctr]));
		}
		return;
	}

	public boolean isEmailSyntaxValid(String email) {
		return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public boolean doesHostExist(String email) {
		String host = email.substring(email.indexOf("@")+1);
		try {
			Inet4Address.getByName(host);
		} catch (UnknownHostException e) {
			logger.info("[mail validation] host of mail does not exist email="+email +" - "+ e.getMessage());
			return false;
		}
		return true;
	}   

	private ArrayList<String> getMX(String hostName) throws NamingException {
		// Perform a DNS lookup for MX records in the domain
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.naming.factory.initial",
				"com.sun.jndi.dns.DnsContextFactory");
		DirContext ictx = new InitialDirContext(env);
		Attributes attrs = ictx.getAttributes(hostName, new String[] { "MX" });
		Attribute attr = attrs.get("MX");

		// if we don't have an MX record, try the machine itself
		if ((attr == null) || (attr.size() == 0)) {
			attrs = ictx.getAttributes(hostName, new String[] { "A" });
			attr = attrs.get("A");
			if (attr == null)
				throw new NamingException("No match for name '" + hostName
						+ "'");
		}
		// Huzzah! we have machines to try. Return them as an array list
		// NOTE: We SHOULD take the preference into account to be absolutely
		// correct. This is left as an exercise for anyone who cares.
		ArrayList<String> res = new ArrayList<String>();
		NamingEnumeration<?> en = attr.getAll();

		while (en.hasMore()) {
			String mailhost;
			String x = (String) en.next();
			String f[] = x.split(" ");
			// THE fix *************
			if (f.length == 1)
				mailhost = f[0];
			else if (f[1].endsWith("."))
				mailhost = f[1].substring(0, (f[1].length() - 1));
			else
				mailhost = f[1];
			// THE fix *************
			res.add(mailhost);
		}

		logger.debug("MX hosts:\n" + KStringUtil.join(res, "\n"));
		return res;
	}

	public boolean mayMailboxExist( String address ) {
		if (!KValidator.isEmail(address) || !isValidEmailAddress(address)) {
			return false;
		}

		// Find the separator for the domain name
		int pos = address.indexOf( '@' );

		// If the address does not contain an '@', it's not valid
		if ( pos == -1 ) return false;

		// Isolate the domain/machine name and get a list of mail exchangers
		String domain = address.substring( ++pos );
		ArrayList<String> mxList = null;
		try {
			mxList = getMX( domain );
		}
		catch (CommunicationException ce) {
			logger.info("[mail validation] got dns problems email="+address, ce);
			return true;
		} catch (NamingException ex) {
			logger.info("[mail validation] got host naming exception for email="+address +" - "+ ex);
			return false;
		}

		// if we do not find an mx, we beleve the adress anyway
		//if ( mxList.size() == 0 ) return true;
        
		if ( mxList.size() == 0 ) return false;
        return true;
        //return connectMX(address, mxList);

	}
    
	public boolean connectMX(String address, List<String> mxList) {
		// modification, SMa: mx only use the first mx
		int mx = 0;
		try {
			int res;
			//
			String mxHost = mxList.get(mx);
			logger.debug("Connecting to " + mxHost + " ...");

			Socket skt = new Socket(mxHost, 25);
			BufferedReader rdr = new BufferedReader
					( new InputStreamReader( skt.getInputStream() ) );
			BufferedWriter wtr = new BufferedWriter
					( new OutputStreamWriter( skt.getOutputStream() ) );

			res = hear( rdr );
			if ( res != 220 ) {
				skt.close();
				throw new Exception( "Invalid header" );
			}
			say( wtr, "EHLO "+ this.fromDomain );

			res = hear( rdr );
			if ( res != 250 ) {
				skt.close();
				throw new Exception( "Not ESMTP" );
			}

			// validate the sender address              
			say( wtr, "MAIL FROM: <"+this.fromEmail+">" );
			res = hear( rdr );
			if ( res != 250 ) {
				skt.close();
				throw new Exception( "Sender rejected" );
			}

			say( wtr, "RCPT TO: <" + address + ">" );
			res = hear( rdr );

			// be polite
			say( wtr, "RSET" ); hear( rdr );
			say( wtr, "QUIT" ); hear( rdr );

			rdr.close();
			wtr.close();
			skt.close();

			if ( res == 550 ) {
				logger.info("[mail validation] got response SMTP 550 for email="+address);
				return false;
			}

		} catch (Exception e) {
			logger.info("[mail validation] remote mail validation error. Accepting email anyway: email="+address +" - "+ e.getMessage());
		}
		return true;
	}

	private int hear(BufferedReader in) throws IOException {
		String line = null;
		int res = 0;

		while ((line = in.readLine()) != null) {
			String pfx = line.substring(0, 3);
			try {
				res = Integer.parseInt(pfx);
			} catch (Exception ex) {
				res = -1;
			}
			if (line.charAt(3) != '-')
				break;
		}

		return res;
	}

	private void say(BufferedWriter wr, String text) throws IOException {
		wr.write(text + "\r\n");
		wr.flush();

		return;
	}

}
