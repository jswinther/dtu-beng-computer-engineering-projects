function POVv = PowerSpec(v)
%
%   Computes the effect spectrum of v by means of
%   the matrix version of DFT
%
    N = length(v);
    [U,F,G] = UFGDFT(N);
    DFTv = F*v;
    POVv = (DFTv.*conj(DFTv))/(N*N);
end

