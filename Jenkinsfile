pipeline {
    agent any
    tools {
        maven 'maven'
    }

    parameters {
        string(name: "CODE_COVERAGE_QG", defaultValue: '0.5', description: 'Code coverage quality gate')
    }


    stages {
    	stage('Pre-Build') {
    		parallel {
    			stage('Unit Tests') {
            		steps{
                		sh "mvn -f pom.xml clean verify test -Dsuitename=unit_tests.xml -Dcoverage_quality_gate=${params.CODE_COVERAGE_QG}"
            		}
            		post {
        				always {
            				step( [ $class: 'JacocoPublisher' ] )
            				step( [ $class: 'JUnitTestReportPublisher', fileIncludePattern: 'target/surefire-reports/junitreports/*.xml' ])
        				}
    				}
        		}
    		}
    	}

    	stage ('Upload artifact') {
    		when {
                branch 'master'
            }
            steps {
                rtServer (
                    id: "ARTIFACTORY_SERVER",
                    url: "http://localhost:8080/artifactory",
		    credentialsId: 'ahmed.Admin@123'
                )

                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "ARTIFACTORY_SERVER",
                    releaseRepo: "libs-release-local",
                    snapshotRepo: "libs-snapshot-local"
                    deployArtifacts = false
                )

                rtMavenResolver (
                    id: "MAVEN_RESOLVER",
                    serverId: "ARTIFACTORY_SERVER",
                    releaseRepo: "libs-release",
                    snapshotRepo: "libs-snapshot"
                )

                rtMavenRun (
                    tool: "maven", // Tool name from Jenkins configuration
                    pom: 'pom.xml',
                    goals: " install -Djacoco.skip=true -Dmaven.test.skip=true -Dcoverage_quality_gate=${params.CODE_COVERAGE_QG}",
                    deployerId: "MAVEN_DEPLOYER",
                    resolverId: "MAVEN_RESOLVER"
                )

                rtPublishBuildInfo (
                    serverId: "ARTIFACTORY_SERVER"
                )
            }
        }
    }
}