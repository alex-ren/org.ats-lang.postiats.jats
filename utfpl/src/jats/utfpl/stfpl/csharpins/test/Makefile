
RM = rm -rf

PATHOME = $(HOME)/programs/tempPAT

TARGET = 03_closure_dats.exe \

all: $(TARGET)

03_closure_dats.exe: 03_closure_dats.cs postiats_ccomp.cs
	mcs $^

test:
	mono 03_closure_dats.exe


clean:
	$(RM) $(TARGET)



