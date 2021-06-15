function A = MakeToeplitz(a)
%
%   Returns a Toeplitz matrix with a as its first column
%
    N = length(a);
    A = zeros(N);
    for col = 1:N
        A(col:N, col) = a(1:N-col+1);
    end
end

