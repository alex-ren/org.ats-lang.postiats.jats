
RM = rm -rf

PATHOME = $(HOME)/programs/tempPAT

SOURCE = Allocator.cs \
	 FrameStackUtil.cs \
         FLenStack.cs \
	 FStackNode.cs \
	 PStack.cs \
	 ThreadAllocator.cs \
         \
	 MyLib.cs \
	 SysLinkedNode.cs \
	 MyLibObj.cs \
         Tuple.cs \
	 ValueArray.cs \
	 MemHolder.cs \
	 AtomRefManager.cs \
	 ArrayRefManager.cs \

TARGET = MyLib.dll

all: $(TARGET)

$(TARGET): $(SOURCE)
	mcs $(SOURCE) -lib:$(PATHOME) -r:PAT.Common.dll -target:library -out:$(TARGET)

install:
	cp $(TARGET) $(PATHOME)/Lib

clean:
	$(RM) $(TARGET)



