function y = Normaliseret(x)
%FD_NORMALISERET Filters input x and returns output y.

% MATLAB Code
% Generated by MATLAB(R) 9.4 and DSP System Toolbox 9.6.
% Generated on: 26-Nov-2018 13:53:27

%#codegen

% To generate C/C++ code from this function use the codegen command.
% Type 'help codegen' for more information.

persistent Hd;

if isempty(Hd)
    
    Numerator   = [1];         % Numerator coefficient vector
    Denominator = [1 -1 0.5];  % Denominator coefficient vector
    
    Hd = dsp.IIRFilter( ...
        'Structure', 'Direct form I', ...
        'Numerator', Numerator, ...
        'Denominator', Denominator);
end

y = step(Hd,double(x));


% [EOF]
