
int inverse(int n, int m) {
    int d = m, u = 0, s = 1, q, r, t;
    while (n != 0) {
        q = d / n, r = d % n;
        d = n;
        n = r;
        t = u - q * s;
        u = s;
        s = t;
    }
    if (u < 0) u += m;
    return u;
}

int gcd(int a, int b)
{
    int i_ = 1, j_ = 0;
    int i = 0, j = 1;
    int c = a, d = b;

    while (1)
    {
        int q = c / d, r = c % d, t;
        if (r == 0) break;
        c = d; d = r;
        t = i_; i_ = i; i = t - q * i;
        t = j_; j_ = j; j = t - q * j;
    }
    printf("%d,%d\n",i, j);
    return d;
}

int CRT(int r[], int m[], int K) {
    int M = 1, n = 0, i;
    for (i=0; i < K; ++i) M *= m[i];
    for (i=0; i<K; ++i) {
        n += r[i] * M/m[i] * inverse(M/m[i], m[i]);
        n %= M;
    }
    if (n < 0)
        n += M;
    return n;
}

int main() {
    int r[2] = {6, 5};
    int m[2] = {3, 7};
    printf("%d", CRT(r, m, 2));
}
