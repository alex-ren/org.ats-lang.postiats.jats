using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace PAT.Lib
{
    using Frame = FLenStack<Object>;
    using FrameStack = FStackNode<FLenStack<Object>>;

    class FrameStackUtil
    {
        public static FrameStack create()
        {
            return FrameStack.nil();
        }

        public static FrameStack addFrame(FrameStack fs, SysLinkedNode xs)
        {
            Frame fr = Frame.create(xs);
            return FrameStack.cons(fs, fr);
        }

        public static FrameStack newFrame(FrameStack fs)
        {
            return FrameStack.cons(fs, Frame.create());
        }

        public static FrameStack deleteFrame(FrameStack fs)
        {
            return fs.getNext();
        }

        public static FrameStack reloadFrame(FrameStack fs, SysLinkedNode xs)
        {
            Frame fr = Frame.create(xs);
            FrameStack nextFS = fs.getNext();

            return FrameStack.cons(nextFS, fr);
        }

        public static FrameStack push(FrameStack fs, Object v)
        {
            Frame x = fs.getValue();
            FrameStack sStack = fs.getNext();

            Frame x2 = Frame.push(x, v);

            return FrameStack.cons(sStack, x2);
        }

        public static FrameStack retopr(FrameStack fs, ref Object v)
        {
            Frame x = fs.getAtPos(0);
            v = x.getFromTop(0);
            fs = fs.getNext();
            return fs;

        }


        public static Object get(FrameStack fs, int frameno, int index)
        {
            Frame x = fs.getAtPos(frameno);
            return x.getFromBottom(index);
        }

    }
}
