# git workflow
Background: [Feature Branch Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)

**For every new feature, we repeat these steps:**

1. Create a new branch named `feature/my-cool-feature`
	
	```
	git checkout -b feature/my-cool-feature
	```

2. Commit changes and push to your branch countinously

	```
	git add .
	git commit -m "Did stuff"
	git push
	```

3. **Once feature is done:** create a pull request via the GitHub web interface
4. Merge the pull request into master and delete the branch.
5. Start on a new feature and return to step 1.