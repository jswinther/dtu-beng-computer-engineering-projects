function [y] = Simuleret(x)
%SIM_FD_CUMULATIVESUM Summary of this function goes here
%   Detailed explanation goes here
persistent Hd;
if isempty(Hd)
    Hd = x;
        for k = 2:length(x)
            Hd(k) = Hd(k-1) + x(k);
        end
else
    previousHd = Hd;
    x_len = length(x);
    for i = 1:length(x)
        Hd(i) = previousHd(x_len);
    end
    for k = 2:length(x)
        Hd(k) = Hd(k-1) + x(k);
    end
    Hd = Hd + 1;
end 
y = Hd;
end

