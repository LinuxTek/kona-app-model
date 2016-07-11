package com.linuxtek.kona.app.entity;

import java.io.Serializable;

public class KEmailStats implements Serializable {
	private static final long serialVersionUID = 1L;
    
	double failed = 0.0;
	double failedRate = 0.0;
	double delivered = 0.0;
	double deliveredRate = 0.0;
	double bounced = 0.0;
	double bouncedRate = 0.0;
	double complained = 0.0;
	double complainedRate = 0.0;
	double optedOut = 0.0;
	double optedOutRate = 0.0;
	double opened = 0.0;
	double openedAllRate = 0.0;
	double clicked = 0.0;
	double clickedAllRate = 0.0;
	double printed = 0.0;
	double printedAllRate = 0.0;
	double forwarded = 0.0;
	double forwardedAllRate = 0.0;

	double openedDeliveredRate = 0.0;
	double clickedDeliveredRate = 0.0;
	double printedDeliveredRate = 0.0;
	double forwardedDeliveredRate = 0.0;

	double clickedOpenedRate = 0.0;
	double printedOpenedRate = 0.0;
	double forwardedOpenedRate = 0.0;

	double emailCount = 0.0;

	public KEmailStats() {
	}

	public double getFailed() {
		return failed;
	}

	public void setFailed(double failed) {
		this.failed = failed;
	}

	public double getFailedRate() {
		return failedRate;
	}

	public void setFailedRate(double failedRate) {
		this.failedRate = failedRate;
	}
    
	public double getDelivered() {
		return delivered;
	}

	public void setDelivered(double delivered) {
		this.delivered = delivered;
	}

	public double getDeliveredRate() {
		return deliveredRate;
	}

	public void setDeliveredRate(double deliveredRate) {
		this.deliveredRate = deliveredRate;
	}

	public double getBounced() {
		return bounced;
	}

	public void setBounced(double bounced) {
		this.bounced = bounced;
	}

	public double getBouncedRate() {
		return bouncedRate;
	}

	public void setBouncedRate(double bouncedRate) {
		this.bouncedRate = bouncedRate;
	}

	public double getComplained() {
		return complained;
	}

	public void setComplained(double complained) {
		this.complained = complained;
	}

	public double getComplainedRate() {
		return complainedRate;
	}

	public void setComplainedRate(double complainedRate) {
		this.complainedRate = complainedRate;
	}

	public double getOptedOut() {
		return optedOut;
	}

	public void setOptedOut(double optedOut) {
		this.optedOut = optedOut;
	}

	public double getOptedOutRate() {
		return optedOutRate;
	}

	public void setOptedOutRate(double optedOutRate) {
		this.optedOutRate = optedOutRate;
	}

	public double getOpened() {
		return opened;
	}

	public void setOpened(double opened) {
		this.opened = opened;
	}

	public double getOpenedAllRate() {
		return openedAllRate;
	}

	public void setOpenedAllRate(double openedAllRate) {
		this.openedAllRate = openedAllRate;
	}

	public double getClicked() {
		return clicked;
	}

	public void setClicked(double clicked) {
		this.clicked = clicked;
	}

	public double getClickedAllRate() {
		return clickedAllRate;
	}

	public void setClickedAllRate(double clickedAllRate) {
		this.clickedAllRate = clickedAllRate;
	}

	public double getPrinted() {
		return printed;
	}

	public void setPrinted(double printed) {
		this.printed = printed;
	}

	public double getPrintedAllRate() {
		return printedAllRate;
	}

	public void setPrintedAllRate(double printedAllRate) {
		this.printedAllRate = printedAllRate;
	}

	public double getForwarded() {
		return forwarded;
	}

	public void setForwarded(double forwarded) {
		this.forwarded = forwarded;
	}

	public double getForwardedAllRate() {
		return forwardedAllRate;
	}

	public void setForwardedAllRate(double forwardedAllRate) {
		this.forwardedAllRate = forwardedAllRate;
	}

	public double getOpenedDeliveredRate() {
		return openedDeliveredRate;
	}

	public void setOpenedDeliveredRate(double openedDeliveredRate) {
		this.openedDeliveredRate = openedDeliveredRate;
	}

	public double getClickedDeliveredRate() {
		return clickedDeliveredRate;
	}

	public void setClickedDeliveredRate(double clickedDeliveredRate) {
		this.clickedDeliveredRate = clickedDeliveredRate;
	}

	public double getPrintedDeliveredRate() {
		return printedDeliveredRate;
	}

	public void setPrintedDeliveredRate(double printedDeliveredRate) {
		this.printedDeliveredRate = printedDeliveredRate;
	}

	public double getForwardedDeliveredRate() {
		return forwardedDeliveredRate;
	}

	public void setForwardedDeliveredRate(double forwardedDeliveredRate) {
		this.forwardedDeliveredRate = forwardedDeliveredRate;
	}

	public double getClickedOpenedRate() {
		return clickedOpenedRate;
	}

	public void setClickedOpenedRate(double clickedOpenedRate) {
		this.clickedOpenedRate = clickedOpenedRate;
	}

	public double getPrintedOpenedRate() {
		return printedOpenedRate;
	}

	public void setPrintedOpenedRate(double printedOpenedRate) {
		this.printedOpenedRate = printedOpenedRate;
	}

	public double getForwardedOpenedRate() {
		return forwardedOpenedRate;
	}

	public void setForwardedOpenedRate(double forwardedOpenedRate) {
		this.forwardedOpenedRate = forwardedOpenedRate;
	}

	public double getEmailCount() {
		return emailCount;
	}

	public void setEmailCount(double emailCount) {
		this.emailCount = emailCount;
	}



}
