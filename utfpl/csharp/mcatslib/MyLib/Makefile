
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

test: test11.exe

test11.exe: Test.cs $(SOURCE)
	dmcs -lib:$(PATHOME) -r:PAT.Common.dll -target:exe -out:test11.exe Test.cs $(SOURCE)

install:
	cp $(TARGET) $(PATHOME)/Lib

clean:
	$(RM) $(TARGET) test11.exe



