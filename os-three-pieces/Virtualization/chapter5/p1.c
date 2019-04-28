#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main(int argc, char *argv[])
{
    printf("Hello main pid->%d\n", (int)getpid());
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork failed\n");
    } else if(rc == 0) {//Child process
        printf("child process pid->%d\n", (int) getpid());
    } else {//parent process
        printf("parent rc->%d\n", rc);
        printf("parent process pid->%d\n", (int) getpid());
    }
}

