using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using PAT.Lib;

namespace MyLib
{
    public class ThreadAllocator: Allocator
    {
        public ThreadAllocator(int n): base(n) {
        }
    }
}
