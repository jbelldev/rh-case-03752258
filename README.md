# REPRODUCER FOR RH CASE 03752258

This is a reproducer for the Red Hat Support Case: 03752258. Contents include a simple Quarkus file that uses dev services, and a simple tekton project for retrieving and source code, verifying the contents using a custom-maven-task, and finally building the project using the same custom-maven-task.

Red Hat Support Case Link: [CASE 03572258](https://access.redhat.com/support/cases/#/case/03752258)

## PREREQUISITES

1. Have a namespace called case03752258 (the setup creates this for you, be sure to comment out if you do not want this to occur).
2. Create a custom git-image on the cluster (see ./setup/git-image-setup/git-task.yaml). `oc create --save-config=true -f {relative-path-to-file}/git-task.yaml`.
3. Have openshift-piplines operator installed on the cluster
4. Make namespace adjustments within these files from **case03752258** if necessary.

## REPRODUCING ERROR

1. Ensure the prerequisites above are fullfilled.
2. Have `oc` installed and operable.
3. Sign-in to your cluster.
4. Change to the /tekton/setup directory
5. Run the ./setup.sh
6. Change to the /tekton/execution directory
7. Run the **PLR** `oc create -f ./test-and-build-pr.yaml`

### VERIFICATION

In the `/tekton/setup/tasks/execute-maven-task.yaml` file, I have commented out the two base images. It is setup here using the Red Hat image which fails. However, the pipeline will succeed if you switch to the `gcr.io` image base.

### MAKING CHANGES TO QUARKUS FILES AND TESTING

If you determine some changes need to be applied within the Quarkus project files, perform the following to update the **PLR**.

1. Update quarkus files
2. COMMIT CHANGES TO REPO
3. PUSH CHANGES TO REPO
4. Get the `commit id` from the latest PUSH
5. Update the `image-tag` value of the `/tekton/execution/test-and-build-pr.yaml` file
6. Re-run the **PLR**.


## TEARDOWN

1. Have `oc` installed and operable.
2. Sign-in to your cluster.
3. Change to the /tekton/teardown directory
4. Run the ./teardown.sh