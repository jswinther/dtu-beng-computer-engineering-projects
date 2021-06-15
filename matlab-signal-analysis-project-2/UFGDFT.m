function [U, F, G] = UFGDFT(N)
%
%   Compute the matrices for DFT
%
%   U is the unitary "in-between" matrix
%   F is the matrix of the DFT
%   G is the inverse of F
%
    F = zeros(N);
    for row = 0:N-1
        for col = 0:N-1
            F(row+1, col+1) = exp(-2*pi*i*row*col/N);
        end
    end
    U = F/sqrt(N);
    G = F'/N;
end