#!/bin/bash

DIR="./app"
function update() {
    OLD=$1
    NEW=$2

    echo ""
    echo -n "Updating [$OLD] -> [$NEW] ... "
    find $DIR -type f -exec perl -pi -e "s/${OLD}/${NEW}/g" {} \;
    echo "done"
    echo ""
}

update "lastUpdated" "updatedDate"
update "LastUpdated" "UpdatedDate"

