# REPRODUCER FOR RH CASE 03752258

This is a reproducer for the Red Hat Support Case: 03752258. Contents include a simple Quarkus file that uses dev services, and a simple tekton project for retrieving and source code, verifying the contents using a custom-maven-task, and finally building the project using the same custom-maven-task.

Case Link: [CASE 03572258](https://access.redhat.com/support/cases/#/case/03752258)

## PREREQUISITES

1. Have a namespace called case03752258 (the setup creates this for you, be sure to comment out if you do not want this to occur).
2. Create a custom git-image on the cluster (see ./setup/git-image-setup/git-task.yaml).
3. Make namespace adjustments within these files from **case03752258** if necessary.