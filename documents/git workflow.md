# git workflow
Background: [Feature Branch Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)

**For every new feature, we repeat these steps:**

1. Make sure you are in `develop` branch

	```
	git checkout develop
	git pull origin develop
	```

2. Create a new branch named `my-cool-feature`
	
	```
	git checkout -b my-cool-feature
	git push origin my-cool-feature
	```

3. Commit changes and push to your branch countinously

	```
	git add -A
	git commit -m "Did stuff"
	git push
	```

4. Merge the pull request into `develop` and delete the branch.
	
	```
	git checkout develop
	git pull origin develop
	git merge my-cool-feature
	git push origin develop 
	```

6. Start on a new feature and return to step 1.

## Weekly releases
Once a week, we merge the `develop` branch to `master`.

```
git checkout master
git pull origin master
git merge develop
git push origin master
```