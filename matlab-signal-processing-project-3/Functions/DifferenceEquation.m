function ys = DifferenceEquation(as, bs, xs, xms)
%
%   Step-by-step solution of the linear difference equation
%
%      a0*yk + a1*yk-1 +…+ an*yk-n = b0*xk + b1*xk-1 +…+ bm*xk-m 
%
%   The a's and b's must be input, i.e. as containd a0, a1, ... and
%   bs similarly b0, b1, ... (Note, that MatLab number fro 1).
%
%   The x's are in xs, beginning with x0. Therefore xminus1, xminus2 etc.
%   are in xms (initial conditions, usually zeros).
%
%   This version allows for a0 =|= 1. It extends xs and the two arrays
%   as and bs to cater for initial values
%
    N = length(as); M = length(bs);
    if N > M
        bs = [bs,zeros(1,N-M)];
        P = N;
    else
        as = [as,zeros(1,M-N)];
        P = M;
    end
    
    K  = length(xs) + P-1;
    xs = [xms,xs];
    ys = zeros(1,K);
    
    a0 = as(1);
    for k = P:K
        ys(k) = (-sum(as(2:P).*ys(k-1:-1:k-P+1)) + sum(bs.*xs(k:-1:k-P+1)))/a0;
    end

    ys = ys(P:K);
end

