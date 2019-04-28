#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/wait.h>
int main(int args, char *argv[]) {
    printf("Hello main pid->%d\n", (int) getpid());
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork() failed");
        exit(1);
    } else if(rc == 0) {//Child process
        printf("child process pid->%d\n", (int) getpid());
    } else {//Parent process
        int wc = wait(NULL);
        printf("parent rc->%d wc->%d\n", rc, wc);
        printf("parent process pid->%d\n", (int) getpid());
    }
}
