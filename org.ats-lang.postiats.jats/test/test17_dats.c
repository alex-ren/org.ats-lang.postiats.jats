/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-7-15: 20h:10m
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
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 26347(line=693, offs=1) -- 26378(line=693, offs=32)
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
ATSdyncst_mac(atspre_assert_errmsg_bool1) ;
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
foo2_0(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g0int_int$2(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g0int_int$2$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test17.dats: 47(line=8, offs=5) -- 69(line=8, offs=27)
*/
/*
local: 
global: foo2_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo2_0(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo2_0:
ATSINSmove(tmpret0, arg0) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [foo2_0] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27495(line=738, offs=14) -- 27535(line=738, offs=54)
*/
/*
local: 
global: gt_g0int_int$2$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3214)
tmparg = S2Evar(tk(3214))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g0int_int$2(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret4, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp5, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp5, PMVtmpltcst(g0int2int<S2Eextkind(atstype_int), S2Evar(tk(3214))>)(arg1)) ;
ATSINSmove(tmpret4, PMVtmpltcst(g0int_gt<S2Evar(tk(3214))>)(arg0, tmp5)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret4) ;
} /* end of [ATSLIB_056$prelude_gt_g0int_int$2] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27495(line=738, offs=14) -- 27535(line=738, offs=54)
*/
/*
local: 
global: gt_g0int_int$2$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3214)
tmparg = S2Evar(tk(3214))
tmpsub = Some(tk(3214) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g0int_int$2$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp4$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp5$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp5$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp4$1, atspre_g0int_gt_int(arg0, tmp5$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp4$1) ;
} /* end of [ATSLIB_056$prelude_gt_g0int_int$2$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test17.dats: 87(line=10, offs=17) -- 165(line=14, offs=4)
*/
/*
local: foo2_0$0(level=0)
global: foo2_0$0(level=0), mainats_void_0$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret1, atsvoid_t0ype) ;
ATStmpdec(tmp2, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp3, atstkind_t0ype(atstype_bool)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
ATSINSmove(tmp2, foo2_0(ATSPMVi0nt(3))) ;
/*
letpush(end)
*/

ATSINSmove(tmp3, ATSLIB_056$prelude_gt_g0int_int$2$1(tmp2, ATSPMVi0nt(3))) ;
ATSif(
tmp3
) ATSthen() {
ATSINSmove_void(tmpret1, atspre_assert_errmsg_bool1(ATSPMVbool_false(), ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test17.dats: 135(line=13, offs=17) -- 151(line=13, offs=33)"))) ;
} ATSelse() {
ATSINSmove_void(tmpret1, ATSempty()) ;
} /* ATSendif */
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
