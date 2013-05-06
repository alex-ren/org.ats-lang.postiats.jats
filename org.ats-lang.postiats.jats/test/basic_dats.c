/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-5-2: 18h:24m
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
/*
typedefs-for-tyrecs-and-tysums(end)
*/
/*
dyncstlst-declaration(beg)
*/
ATSdyncst_mac(atspre_assert_errmsg_bool1) ;
/*
dyncstlst-declaration(end)
*/
#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$2$0(0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(2922)
tmparg = S2Evar(tk(2922))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret4, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp5, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp5, PMVtmpltcst(g1int2int<S2EVar(399->S2Eextkind(atstype_int)), S2EVar(400->S2Evar(tk(2922)))>)(arg1)) ;
ATSINSmove(tmpret4, PMVtmpltcst(g1int_eq<S2Evar(tk(2922))>)(arg0, tmp5)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret4) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$2$1(1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(2922)
tmparg = S2Evar(tk(2922))
tmpsub = Some(tk(2922) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp4$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp5$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp5$1, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp4$1, atspre_g1int_eq_int(arg0, tmp5$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp4$1) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/basic.dats: 208(line=17, offs=7) -- 283(line=24, offs=4)
*/
/*
local: 
global: mainats_void_0$0$0(0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret0, atsvoid_t0ype) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp2, atsvoid_t0ype) ;
ATStmpdec(tmp3, atstkind_t0ype(atstype_bool)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
ATSINSmove(tmp1, atspre_g1int_add_int(ATSPMVi0nt(1), ATSPMVi0nt(1))) ;
ATSINSmove(tmp3, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$2$1(tmp1, ATSPMVi0nt(2))) ;
ATSINSmove_void(tmp2, atspre_assert_errmsg_bool1(tmp3, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/basic.dats: 243(line=20, offs=10) -- 259(line=20, offs=26)"))) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret0, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret0) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */