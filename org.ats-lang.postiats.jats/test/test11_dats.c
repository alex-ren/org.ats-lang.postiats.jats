/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-4-26: 15h:37m
**
*/

/*
** include runtime header files
*/
#ifndef _ATS_CCOMP_HEADER_NONE
#include "pats_ccomp_config.h"
#include "pats_ccomp_basics.h"
#include "pats_ccomp_typedefs.h"
#include "pats_ccomp_instrset.h"
#include "pats_ccomp_exception.h"
#include "pats_ccomp_memalloc.h"
#include "pats_ccomp_memalloca.h"
#endif /* _ATS_CCOMP_HEADER_NONE */


/*
** include prelude cats files
*/
#ifndef _ATS_CCOMP_PRELUDE_NONE
//
#include "prelude/CATS/basics.cats"
#include "prelude/CATS/integer.cats"
#include "prelude/CATS/memory.cats"
#include "prelude/CATS/pointer.cats"
#include "prelude/CATS/bool.cats"
#include "prelude/CATS/char.cats"
#include "prelude/CATS/float.cats"
#include "prelude/CATS/string.cats"
#include "prelude/CATS/strptr.cats"
//
#include "prelude/CATS/filebas.cats"
//
#include "prelude/CATS/list.cats"
#include "prelude/CATS/option.cats"
#include "prelude/CATS/array.cats"
#include "prelude/CATS/arrayptr.cats"
#include "prelude/CATS/arrayref.cats"
#include "prelude/CATS/matrix.cats"
#include "prelude/CATS/matrixptr.cats"
//
#endif /* _ATS_CCOMP_PRELUDE_NONE */

/*
staload-prologues(beg)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 25237(line=637, offs=1) -- 25268(line=637, offs=32)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1379(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdlib.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1814(line=54, offs=1) -- 1854(line=55, offs=33)
*/
/*
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
typedef
struct {
atstkind_t0ype(atstype_int) atslab$0; 
atstype_boxed atslab$1; 
} postiats_tyrec_0 ;
typedef
struct {
atstkind_t0ype(atstype_int) atslab$0; 
atstkind_t0ype(atstype_int) atslab$1; 
} postiats_tyrec_1 ;
/*
typedefs-for-tyrecs-and-tysums(end)
*/
/*
dyncstlst-declaration(beg)
*/
/*
dyncstlst-declaration(end)
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo_0(atstype_boxed) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test11.dats: 77(line=7, offs=5) -- 98(line=7, offs=26)
*/
/*
local: 
global: foo_0$0(0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo_0(atstype_boxed arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo_0:
ATSINSmove(tmpret0, ATSselboxrec(arg0, postiats_tyrec_0, atslab$0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [foo_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test11.dats: 116(line=9, offs=17) -- 248(line=18, offs=7)
*/
/*
local: foo_0$0(0)
global: foo_0$0(0), mainats_void_0$1$0(0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret1, atsvoid_t0ype) ;
ATStmpdec(tmp2, atstype_boxed) ;
ATStmpdec(tmpref3, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp4, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp5, atstype_boxed) ;
ATStmpdec(tmp6, atstype_boxed) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
ATSINSmove_boxrec(tmp2, postiats_tyrec_1) ;
ATSINSstore_boxrec_ofs (tmp2, postiats_tyrec_1, atslab$0, ATSPMVi0nt(4)) ;
ATSINSstore_boxrec_ofs (tmp2, postiats_tyrec_1, atslab$1, ATSPMVi0nt(6)) ;
/*
ATSINStmpdec(tmpref3)) ;
*/
ATSINSmove_boxrec(tmpref3, postiats_tyrec_0) ;
ATSINSstore_boxrec_ofs (tmpref3, postiats_tyrec_0, atslab$0, ATSPMVi0nt(1)) ;
ATSINSstore_boxrec_ofs (tmpref3, postiats_tyrec_0, atslab$1, tmp2) ;
ATSINSmove(tmp4, foo_0(tmpref3)) ;
ATSINSmove_boxrec(tmp6, postiats_tyrec_1) ;
ATSINSstore_boxrec_ofs (tmp6, postiats_tyrec_1, atslab$0, ATSPMVi0nt(2)) ;
ATSINSstore_boxrec_ofs (tmp6, postiats_tyrec_1, atslab$1, ATSPMVi0nt(3)) ;
ATSINSmove_boxrec(tmp5, postiats_tyrec_0) ;
ATSINSstore_boxrec_ofs (tmp5, postiats_tyrec_0, atslab$0, ATSPMVi0nt(1)) ;
ATSINSstore_boxrec_ofs (tmp5, postiats_tyrec_0, atslab$1, tmp6) ;
ATSINSmove(tmp7, foo_0(tmp5)) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret1, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret1) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag) ;
} /* ATSendif */
ATSreturn_void() ;
} /* end of [*_dynload] */

/*
** the ATS runtime
*/
#ifndef _ATS_CCOMP_RUNTIME_NONE
#include "pats_ccomp_runtime.c"
#endif /* _ATS_CCOMP_RUNTIME_NONE */

/*
** the [main] implementation
*/
int
main
(
int argc, char **argv, char **envp
) {
int err = 0 ;
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */