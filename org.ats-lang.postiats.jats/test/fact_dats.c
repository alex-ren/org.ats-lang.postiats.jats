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
ATSdyncst_mac(atspre_print_string) ;
ATSdyncst_mac(atspre_print_int) ;
ATSdyncst_mac(atspre_print_newline) ;
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
fact_0(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g1int_int$1$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$8(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$8$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats_void_int() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/fact.dats: 368(line=19, offs=5) -- 665(line=37, offs=4)
*/
/*
local: fact_0$0(level=0)
global: fact_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
fact_0(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp6, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp8, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_fact_0:
/*
letpush(beg)
*/
/*
letpush(end)
*/

ATSINSmove(tmp1, ATSLIB_056$prelude_gt_g1int_int$1$1(arg0, ATSPMVi0nt(0))) ;
ATSif(
tmp1
) ATSthen() {
/*
letpush(beg)
*/
ATSINSmove(tmp7, atspre_g1int_sub_int(arg0, ATSPMVi0nt(1))) ;
ATSINSmove(tmp6, fact_0(tmp7)) ;
/* (*nothing*) */
ATSINSmove(tmp8, atspre_g1int_mul_int(arg0, tmp6)) ;
/*
letpush(end)
*/

ATSINSmove(tmpret0, tmp8) ;
/*
INSletpop()
*/
} ATSelse() {
ATSINSmove(tmpret0, ATSPMVi0nt(1)) ;
} /* ATSendif */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [fact_0] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28010(line=756, offs=14) -- 28050(line=756, offs=54)
*/
/*
local: 
global: gt_g1int_int$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3225)
tmparg = S2Evar(tk(3225))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g1int_int:
ATSINSmove(tmp3, PMVtmpltcst(g1int2int<S2Eextkind(atstype_int), S2Evar(tk(3225))>)(arg1)) ;
ATSINSmove(tmpret2, PMVtmpltcst(g1int_gt<S2Evar(tk(3225))>)(arg0, tmp3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret2) ;
} /* end of [ATSLIB_056$prelude_gt_g1int_int$1] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28010(line=756, offs=14) -- 28050(line=756, offs=54)
*/
/*
local: 
global: gt_g1int_int$1$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3225)
tmparg = S2Evar(tk(3225))
tmpsub = Some(tk(3225) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gt_g1int_int$1$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g1int_int:
ATSINSmove(tmp3$1, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp2$1, atspre_g1int_gt_int(arg0, tmp3$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp2$1) ;
} /* end of [ATSLIB_056$prelude_gt_g1int_int$1$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$8$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3231)
tmparg = S2Evar(tk(3231))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$8(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret19, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp20, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp20, PMVtmpltcst(g1int2int<S2Eextkind(atstype_int), S2Evar(tk(3231))>)(arg1)) ;
ATSINSmove(tmpret19, PMVtmpltcst(g1int_eq<S2Evar(tk(3231))>)(arg0, tmp20)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret19) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$8] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$8$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3231)
tmparg = S2Evar(tk(3231))
tmpsub = Some(tk(3231) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$8$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp19$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp20$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp20$1, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp19$1, atspre_g1int_eq_int(arg0, tmp20$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp19$1) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$8$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/fact.dats: 702(line=40, offs=6) -- 847(line=46, offs=2)
*/
/*
local: fact_0$0(level=0)
global: fact_0$0(level=0), mainats_void_int$7$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats_void_int()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret9, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp10, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp11, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp12, atsvoid_t0ype) ;
ATStmpdec_void(tmp13, atsvoid_t0ype) ;
ATStmpdec_void(tmp14, atsvoid_t0ype) ;
ATStmpdec_void(tmp15, atsvoid_t0ype) ;
ATStmpdec_void(tmp16, atsvoid_t0ype) ;
ATStmpdec_void(tmp17, atsvoid_t0ype) ;
ATStmpdec(tmp18, atstkind_t0ype(atstype_bool)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_int:
/*
letpush(beg)
*/
/* (*nothing*) */
ATSINSmove(tmp10, fact_0(ATSPMVi0nt(12))) ;
ATSINSmove(tmp11, ATSSELrecsin(tmp10, atstkind_t0ype(atstype_int), atslab$1)) ;
ATSINSmove_void(tmp12, atspre_print_string(ATSPMVstring("fact("))) ;
ATSINSmove_void(tmp13, atspre_print_int(ATSPMVi0nt(12))) ;
ATSINSmove_void(tmp14, atspre_print_string(ATSPMVstring(") = "))) ;
ATSINSmove_void(tmp15, atspre_print_int(tmp11)) ;
ATSINSmove_void(tmp16, atspre_print_newline()) ;
ATSINSmove(tmp18, ATSLIB_056$prelude_eq_g1int_int$8$1(tmp11, ATSPMVi0nt(479001600))) ;
ATSINSmove_void(tmp17, atspre_assert_errmsg_bool1(tmp18, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/fact.dats: 817(line=45, offs=12) -- 843(line=45, offs=38)"))) ;
/*
letpush(end)
*/

ATSINSmove(tmpret9, ATSPMVi0nt(0)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret9) ;
} /* end of [mainats_void_int] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynload() ;
ATSmainats_void_int(err) ;
return (err) ;
} /* end of [main] */
