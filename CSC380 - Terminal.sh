#!/bin/sh

mainDir="Documents/CSC380"; #put your directory here
cd $mainDir
ls

git pull

read -p "Enter commit comment: " comment

git add -A
git commit -m $comment
git push

echo "Successfully executed!"

exit
