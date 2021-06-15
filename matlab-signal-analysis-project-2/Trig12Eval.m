function Fs = Trig12Eval(Ts, As, tmin, tmax)
%
% Evaluates the periodic function described by As
%
% The interval in which the original Ts lie is[tmin; tmax]
%
% (During computation, this is rescaled)
%

    w = tmax-tmin;
    N = size(Ts, 2);
    Fs = zeros(1,N);
    Pi = 3.141592653589793;
    for k = 1:N
        t = 2*Pi*(Ts(k) - tmin)/w;
        Fs(k) = As(1) + ...
                As(2)*cos(t)    + As(3)*sin(t) + ...
                As(4)*cos(2*t)  + As(5)*sin(2*t) + ...
                As(6)*cos(3*t)  + As(7)*sin(3*t) + ...
                As(8)*cos(4*t)  + As(9)*sin(4*t) + ...
                As(10)*cos(5*t) + As(11)*sin(5*t) + ...
                As(12)*cos(6*t);
    end
end