#!/bin/bash

modules=$(cat <<EOF
kona-util
kona-media
kona-locale
kona-encryption
kona-sequence-flake
kona-i18n
kona-data
kona-mailer
kona-io
kona-premailer
kona-templates
kona-http
kona-remote
kona-stripe
kona-app-model
kona-rest
kona-velocity
EOF
)

for module in $modules
do
    echo "Updating $module ..."

    cd $module > /dev/null 2>&1

    if [[ $? != 0 ]]; then
        echo ""
        echo "Error: module does not exist. Aborting."
        echo ""
        exit 1
    fi

    git pull

    if [[ $? != 0 ]]; then
        echo ""
        echo "Error updating code base. Aborting."
        echo ""
        exit 1
    fi

    mvn clean install

    if [[ $? != 0 ]]; then
        echo ""
        echo "Error compiling code. Aborting."
        echo ""
        exit 1
    fi

    cd ../

done
