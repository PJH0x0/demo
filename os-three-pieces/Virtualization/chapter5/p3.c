#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/wait.h>
int main(int args, char *argv[]) {
    printf("Hello main pid->%d\n", (int) getpid());
    int rc = fork();
    if (rc < 0) {
        fprintf(stderr, "fork() failed");
        exit(1);
    } else if(rc == 0) {//Child process
        printf("child process pid->%d\n", (int) getpid());
        char *args[3];
        args[0] = strdup("wc");
        args[1] = strdup("p3.c");
        args[2] = NULL;
        execvp(args[0], args);
        printf("Here is running");
    } else {//Parent process
        int wc = wait(NULL);
        printf("parent rc->%d wc->%d\n", rc, wc);
        printf("parent process pid->%d\n", (int) getpid());
    }
}
