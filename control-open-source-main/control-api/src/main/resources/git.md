# create a new branch
git branch branch-name
# change environment to the new branch
git checkout branch-name

# create a new branch
git branch new-branch
# change environment to the new branch
git checkout new-branch
# create a change
touch new-file.js
# commit the change
git add .
git commit -m "add new file"
# push to a new branch
git push --set-upstream origin new-branch

git fetch origin

git branch -a

git checkout -b fix-failing-tests origin/fix-failing-tests

git checkout -b develop origin/develop