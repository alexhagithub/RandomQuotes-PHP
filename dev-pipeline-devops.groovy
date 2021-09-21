@Library('devops-scripts@main')
import sharedlib.JenkinsfileUtil

def project = "ESMS"
def product = "esikamultisite"
static final PROJECT_REPOSITORY = "https://github.com/OctopusSamples/RandomQuotes-PHP.git"
static final GITHUB_TOKEN_CREDENTIAL = "usergh"
this.language = 'php'
this.propertiesName = 'project-test.properties'
this.propertiesPath = "/tmp/" + this.propertiesName
this.workspace = PWD() 
def utils = new JenkinsfileUtil(this)

def repositoryUrl = scm.userRemoteConfigs[0].url
def workspace = PWD()

println repositoryUrl
println workspace
println WORKSPACE

try{
    node{
        stage('Preparation'){
            //TODO: DELTAS, CLEAN WS CHECKOUT SCM
            //git branch: 'master', url: PROJECT_REPOSITORY, credentialsId: GITHUB_TOKEN_CREDENTIAL
            //git branch: 'master', url: PROJECT_REPOSITORY
        }

        stage('Unit Testing'){
            //TODO: JUNIT OR OTHER TEST FW
        }

        stage('QualityCode'){                        
            //utils.scanningQualityCode()
            // Quality Gate
            sh "echo 'starting QualityGate'"
            //utils.getQualityGate()
        }

        stage('Security'){
            //TODO: GIT LEAKS / AVALORA
            utils.scanningSecutiry()
        }

        stage('Build'){
            //TODO: BUILD
        }

        stage('Contenerize'){
            //TODO: DOCKER BUILD (IF POSSIBLE)
        }

        stage('Artifact Publish'){
            //TODO: UP TO NEXUS
        }
    }
}catch (Exception e){
    node{
        //utils.notifyByMail('FAIL', recipients) //Generar esta funcion
        sh 'echo THERE WAS AN ERROR, REPORT TO DEVOPS TEAM!'
        throw e
    }
}
