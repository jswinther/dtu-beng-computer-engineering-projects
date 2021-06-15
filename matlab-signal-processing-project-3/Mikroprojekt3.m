% Tilføjer paths.
clear all;
addpath('Functions/');
%% Funktion 1 og 2: Modellerer r-tidsrækker.
% 5 forskellige r-tidsrækker.
R1 = [1 1 1 1 1 1 1 1]'; %konstant
R2 = [1 9 2 8 3 7 4 6]'; %store til små udsving
R3 = [2 2 2 5 2 2 2 2]'; %konstant pånær et lille bump
R4 = [1 2 1 -1 1 2 -1 2]'; % hurtigere og hutigere udsving
R5 = [1 1 1 1 4 4 4 4]'; %omslag i værdi, mere eller mindre brat


% FilterSum1 = Cumulative(R1);
% FilterSum2 = Cumulative(R2);
% FilterSum3 = Cumulative(R3);
FilterSum4 = Cumulative(R4);
% FilterSum5 = Cumulative(R5);
% Simuleret1 = Simuleret(R1);
% Simuleret2 = Simuleret(R2);
% Simuleret3 = Simuleret(R3);
Simuleret4 = Simuleret(R4);
% Simuleret5 = Simuleret(R5);
% Normal1 = Normaliseret(R1);
% Normal2 = Normaliseret(R2);
% Normal3 = Normaliseret(R3);
Normal4 = Normaliseret(R4);
% Normal5 = Normaliseret(R5);

% T=table(FilterSum1,Simuleret1,FilterSum2,Simuleret2,FilterSum3,Simuleret3,FilterSum4,Simuleret4,FilterSum5,Simuleret5,Normal1,Normal2,Normal3,Normal4,Normal5)
% T = table(FilterSum1, Simuleret1, Normal1)
% T = table(FilterSum2, Simuleret2, Normal2)
% T = table(FilterSum3, Simuleret3, Normal3)
T = table(FilterSum4, Simuleret4, Normal4)
% T = table(FilterSum5, Simuleret4, Normal5)





%% Init

Kp = 1;     %Proportional (Hvor hurtigt skal det nå target line) jo højere den er, desto mere overshoot bliver der.
Ki = 0.8;     %Integrator (bringer steady-state error til 0)
Kd = 0;     %Derivation (Minimerer overshoot og undershoot, og på den måde øger stabilitetet i kontrolleren)    
s=tf('s');
sys=exp(-s*0.2)/(3*s+1)/(3*s+2);
%% P-kontroller -- Kun proportional
clc;
c = pid(Kp);
step(feedback(c*ss(sys),1)); %Plotter step response
legend off;

%% Pi-kontroller -- Proportional og integral (Vi skal ikke have integral med, men jeg skulle lige se hvad det gjorde)
clc;
close all;
step(1,1); hold on; %Reference line 
step(sys); %Open loop
c = pid(Kp);
step(feedback(c*ss(sys),1)); %Plotter p
c1 =pid(Kp,Ki);
step(feedback(c1*ss(sys),1)); %Plotter PI
legend('Reference line', 'open loop (sys)', 'p','pi');

%% PID-kontroller -- Alle tre argumenter er med
clc;
c = pid(Kp,Ki,Kd);
step(feedback(ss(c*sys),1)); %Plotter step response
legend('Reference line', 'open loop', 'p','pi','pid');
clc;

%% PID tuning
clc;

%Propotional variables
Kp1 = 1;
Kp2 = 2;
Kp3 = 3;
Kp4 = 4;

%Integral variables
Ki1 = 0;
Ki2 = 0;
Ki3 = 0;
Ki4 = 0;

%Derivation variables
Kd1 = 1;
Kd2 = 1;
Kd3 = 1;
Kd4 = 1;

%PID controllers
pid1 = pid(Kp1,Ki1,Kd1);
pid2 = pid(Kp2,Ki2,Kd2);
pid3 = pid(Kp3,Ki3,Kd3);
pid4 = pid(Kp4,Ki4,Kd4);

%Plots
step(1,1); hold on; %Reference line
step(feedback(ss(pid1*sys),1)); %Plotter de forskellige PID controllere
step(feedback(ss(pid2*sys),1)); %Plotter de forskellige PID controllere
step(feedback(ss(pid3*sys),1)); %Plotter de forskellige PID controllere
step(feedback(ss(pid4*sys),1)); %Plotter de forskellige PID controllere
legend('Reference line', 'PID1', 'PID2','PID3','PID4');

clc;