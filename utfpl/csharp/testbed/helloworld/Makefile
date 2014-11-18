
RM = rm -rf


SOURCE = Adder.cs helloworld.cs

TARGET = helloworld.exe

all: $(TARGET)

$(TARGET): $(SOURCE)
	mcs $(SOURCE) -out:$(TARGET)

clean:
	$(RM) $(TARGET)

