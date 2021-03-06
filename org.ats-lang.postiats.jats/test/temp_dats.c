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
ATSdyncst_mac(atspre_assert_errmsg_bool0) ;
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
f91_0(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$1(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$1$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$8(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$8$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/temp.dats: 44(line=5, offs=5) -- 111(line=6, offs=48)
*/
/*
local: f91_0$0(level=0)
global: f91_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
f91_0(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp6, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_f91_0:
ATSINSmove(tmp1, ATSLIB_056$prelude_gte_g0int_int$1$1(arg0, ATSPMVi0nt(101))) ;
ATSif(
tmp1
) ATSthen() {
ATSINSmove(tmpret0, atspre_g0int_sub_int(arg0, ATSPMVi0nt(10))) ;
} ATSelse() {
ATSINSmove(tmp7, atspre_g0int_add_int(arg0, ATSPMVi0nt(11))) ;
ATSINSmove(tmp6, f91_0(tmp7)) ;
ATSINSmove(tmpret0, f91_0(tmp6)) ;
} /* ATSendif */
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [f91_0] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27564(line=740, offs=15) -- 27605(line=740, offs=56)
*/
/*
local: 
global: gte_g0int_int$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3215)
tmparg = S2Evar(tk(3215))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$1(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gte_g0int_int:
ATSINSmove(tmp3, PMVtmpltcst(g0int2int<S2Eextkind(atstype_int), S2Evar(tk(3215))>)(arg1)) ;
ATSINSmove(tmpret2, PMVtmpltcst(g0int_gte<S2Evar(tk(3215))>)(arg0, tmp3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret2) ;
} /* end of [ATSLIB_056$prelude_gte_g0int_int$1] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27564(line=740, offs=15) -- 27605(line=740, offs=56)
*/
/*
local: 
global: gte_g0int_int$1$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3215)
tmparg = S2Evar(tk(3215))
tmpsub = Some(tk(3215) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$1$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gte_g0int_int:
ATSINSmove(tmp3$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp2$1, atspre_g0int_gte_int(arg0, tmp3$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp2$1) ;
} /* end of [ATSLIB_056$prelude_gte_g0int_int$1$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27633(line=742, offs=14) -- 27673(line=742, offs=54)
*/
/*
local: 
global: eq_g0int_int$8$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3216)
tmparg = S2Evar(tk(3216))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$8(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret15, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp16, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g0int_int:
ATSINSmove(tmp16, PMVtmpltcst(g0int2int<S2Eextkind(atstype_int), S2Evar(tk(3216))>)(arg1)) ;
ATSINSmove(tmpret15, PMVtmpltcst(g0int_eq<S2Evar(tk(3216))>)(arg0, tmp16)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret15) ;
} /* end of [ATSLIB_056$prelude_eq_g0int_int$8] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27633(line=742, offs=14) -- 27673(line=742, offs=54)
*/
/*
local: 
global: eq_g0int_int$8$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3216)
tmparg = S2Evar(tk(3216))
tmpsub = Some(tk(3216) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$8$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp15$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp16$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g0int_int:
ATSINSmove(tmp16$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp15$1, atspre_g0int_eq_int(arg0, tmp16$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp15$1) ;
} /* end of [ATSLIB_056$prelude_eq_g0int_int$8$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/temp.dats: 146(line=10, offs=17) -- 264(line=16, offs=7)
*/
/*
local: f91_0$0(level=0)
global: f91_0$0(level=0), mainats_void_0$7$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret8, atsvoid_t0ype) ;
ATStmpdec_void(tmp9, atsvoid_t0ype) ;
ATStmpdec_void(tmp10, atsvoid_t0ype) ;
ATStmpdec(tmp11, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp12, atsvoid_t0ype) ;
ATStmpdec_void(tmp13, atsvoid_t0ype) ;
ATStmpdec(tmp14, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp19, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
ATSINSmove_void(tmp9, atspre_print_string(ATSPMVstring("f91(10) = "))) ;
ATSINSmove(tmp11, f91_0(ATSPMVi0nt(10))) ;
ATSINSmove_void(tmp10, atspre_print_int(tmp11)) ;
ATSINSmove_void(tmp12, atspre_print_newline()) ;
ATSINSmove(tmp19, f91_0(ATSPMVi0nt(0))) ;
ATSINSmove(tmp14, ATSLIB_056$prelude_eq_g0int_int$8$1(tmp19, ATSPMVi0nt(91))) ;
ATSINSmove_void(tmp13, atspre_assert_errmsg_bool0(tmp14, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/temp.dats: 232(line=14, offs=12) -- 255(line=14, offs=35)"))) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret8, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret8) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$temp_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$temp_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$temp_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$temp_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$temp_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
