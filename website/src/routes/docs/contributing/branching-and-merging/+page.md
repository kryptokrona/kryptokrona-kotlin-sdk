---
title: Branching and Merging
---

Git history is similar to code in that it is written once but it is read dozens of times. 
Therefore, we optimize for readability. It should be clear what happened, by whom, when, and ideally why.

## Start all work in a new branch off of master

```
git fetch
git checkout -b your_branch_name origin/master
```

## Rebase onto master daily

This reduces merge conflicts in the future.

```
# on your_branch_name
git fetch
git rebase origin/master
```

## Clean up your commits before creating a PR

When working locally, do whatever you want. Before sharing, clean up your history into logical commits and write good commit messages for them. Focus on clarity for someone reading this 3 months from now.

```
# on your_branch_name
git fetch
git rebase -i origin/master
git push -u origin your_branch_name
```

*Tip: make lots of small commits as you work, then squash them into one or several bigger commits before pushing. 
This helps organize your work, so that logically separate changes can be placed into separate commits without manually splitting an existing commit.*


## Rebase and non-ff-merge into master when you're done

We want a merge commit present for all code merged from branches or PRs, even if that branch or PR is properly rebased and only a single commit. 
The merge commit documents that the merge came from a branch/PR. This helps us differentiate those commits from commits that were made straight to master.

```
# on your_branch_name
git fetch
git rebase origin/master
# if there are conflicts, ask the PR creator to fix them. run `git rebase --abort` to abort
# if no conflicts, proceed ...
git checkout master
git pull
git merge --no-ff your_branch_name
# assuming everything went well...
git push
git push --delete origin your_branch_name
```
**Note:** There's no way to do this exact flow when merging PRs on Github. If you use the "create merge commit" option, it will not rebase. 
If you use "rebase and merge", it will not create a merge commit. In light of this, if you're gonna merge on Github, its ok not to rebase beforehand. 
Please still try to rebase when you can, for example before you create the PR. It this makes the history a lot easier to read.