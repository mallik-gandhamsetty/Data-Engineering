# What is version control?

Version control systems are a category of software tools that help a software team manage changes to source code over time. Version control software keeps track of every modification to the code in a special kind of database. If a mistake is made, developers can turn back the clock and compare earlier versions of the code to help fix the mistake while minimizing disruption to all team members.

## What is Git? 

The most widely used modern version control system in the world today is Git.

### **Git:** 

Download from https://git-scm.com/downloads

### **Bitbucket tutorial:**

https://www.atlassian.com/git/tutorials/learn-git-with-bitbucket-cloud

### **Cheatsheet**

https://services.github.com/on-demand/downloads/github-git-cheat-sheet.pdf

http://files.zeroturnaround.com/pdf/zt_git_cheat_sheet.pdf

### **Git Branching Model**

http://nvie.com/posts/a-successful-git-branching-model/

Git Ref
====

In Git, when you copy a project you say you "clone" it. To work on a git project locally (from your own computer), you will need to clone it. 

Open Git Bash on your machine or open command prompt and Clone below repo - 

```shell
git clone https://github.com/mallik-gandhamsetty/Data-Engineering.git

git --version
```

To view the information that you entered
`git config --global --list`

On your shell, type the following command to add your username:
`git config --global user.name "YOUR_USERNAME"`

Then verify that you have the correct username:
`git config --global user.name`

To set your email address, type the following command:
`git config --global user.email "your_email_address@example.com"`

To verify that you entered your email correctly, type:
`git config --global user.email`

Go to the master branch to pull the latest changes from there
`git checkout master`

Go to the develop branch to pull the latest changes from there
`git checkout develop`

Download the latest changes in the project
`git pull REMOTE NAME-OF-BRANCH -u`
`git pull origin develop`
`git pull origin master`

`git status`

`git add CHANGES IN RED`

`git commit -m "DESCRIBE THE INTENTION OF THE COMMIT"`

`git push REMOTE NAME-OF-BRANCH`
`git push origin develop`

Delete all changes in the Git repository, but leave unstaged things
`git checkout .`

Delete all changes in the Git repository, including untracked files
`git clean -f`

Merge created branch with master branch

You need to be in the created branch.

`git checkout NAME-OF-BRANCH`
`git merge master`

Merge master branch with created branch

You need to be in the master branch.

`git checkout master`
`git merge NAME-OF-BRANCH`

`git checkout -b defect-fix-malllik`

