#include<stdio.h>
int sum(int *a, int n) {
    int i;
    static int s = 0;
    printf("s>>>%d\n", s);
    for (i = 0; i < n; i++) {
        s += a[i];
    }
    return s;
}
