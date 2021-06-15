function [] = Effektspektrum(data, n)
N = length(data);
    F = zeros(N);
    for row = 0:N-1
        for col = 0:N-1
            F(row+1, col+1) = exp(-2*pi*i*row*col/N);
        end
    end
    U = F/sqrt(N);
    G = F'/N;
    
    
    
    [U,F,G] = UFGDFT(N);
    DFTv = F*data;
    POVv = (DFTv.*conj(DFTv))/(N*N);
    figure,plot(log(POVv)), title({'Effektspektrum',n})

end

