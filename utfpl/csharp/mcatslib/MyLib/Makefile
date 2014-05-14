
RM = rm -rf

PATHOME = $(HOME)/programs/tempPAT

SOURCE = Allocator.cs FrameStackUtil.cs LinkedList.cs MyLib.cs SysLinkedNode.cs ValueArray.cs \
       FLenStack.cs FStackNode.cs MemHolder.cs PStack.cs ThreadAllocator.cs

TARGET = MyLib.dll

all: $(TARGET)

$(TARGET): $(SOURCE)
	mcs $(SOURCE) -lib:$(PATHOME) -r:PAT.Common.dll -target:library -out:$(TARGET)

install:
	cp $(TARGET) $(PATHOME)/Lib

clean:
	$(RM) $(TARGET)



