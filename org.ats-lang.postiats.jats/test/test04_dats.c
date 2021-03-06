/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-7-15: 20h:25m
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
#include "pats_ccomp_memalloc.h"
#include "pats_ccomp_memalloca.h"
#include "pats_ccomp_exception.h"
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
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
/*
typedefs-for-tyrecs-and-tysums(end)
*/
/*
dynconlst-declaration(beg)
*/
/*
dynconlst-declaration(end)
*/
/*
dyncstlst-declaration(beg)
*/
/*
dyncstlst-declaration(end)
*/
/*
dynvalist-implementation(beg)
*/
/*
dynvalist-implementation(end)
*/
/*
exnconlst-declaration(beg)
*/
extern void the_atsexncon_initize (atstype_exncon *d2c, char *exnmsg) ;
/*
exnconlst-declaration(end)
*/
/*
assumelst-declaration(beg)
*/
/*
assumelst-declaration(end)
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo1_0(atstkind_type(atstype_ptrk)) ;

ATSstaticdec()
atstkind_type(atstype_ptrk)
foo2_1(atstkind_type(atstype_ptrk)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test04.dats: 46(line=4, offs=5) -- 68(line=4, offs=27)
*/
/*
local: 
global: foo1_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo1_0(atstkind_type(atstype_ptrk) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo1_0:
ATSINSmove(tmpret0, ATSPMVi0nt(3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [foo1_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test04.dats: 73(line=5, offs=5) -- 95(line=5, offs=27)
*/
/*
local: 
global: foo2_1$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_type(atstype_ptrk)
foo2_1(atstkind_type(atstype_ptrk) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret1, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo2_1:
ATSINSmove(tmpret1, arg0) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret1) ;
} /* end of [foo2_1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test04.dats: 113(line=7, offs=17) -- 187(line=14, offs=7)
*/
/*
local: foo1_0$0(level=0), foo2_1$0(level=0)
global: foo1_0$0(level=0), foo2_1$0(level=0), mainats_void_0$2$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret2, atsvoid_t0ype) ;
ATStmpdec(tmpref3, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp4, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp5, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
/*
ATSINStmpdec(tmpref3)) ;
*/
ATSINSmove(tmp4, foo1_0(tmpref3)) ;
ATSINSmove(tmp5, foo2_1(tmpref3)) ;
ATSINSstore(tmpref3, tmp5) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret2, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret2) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
} /* ATSendif */
ATSreturn_void() ;
} /* end of [*_dynload] */

/*
** the ATS runtime
*/
#ifndef _ATS_CCOMP_RUNTIME_NONE
#include "pats_ccomp_runtime.c"
#include "pats_ccomp_runtime2_dats.c"
#include "pats_ccomp_runtime_trywith.c"
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
