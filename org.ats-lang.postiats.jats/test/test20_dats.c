/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-5-2: 21h:14m
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
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/string.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/strptr.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 1533(line=44, offs=1) -- 1572(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1536(line=44, offs=1) -- 1575(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1598(line=48, offs=1) -- 1636(line=48, offs=39)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1380(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdio.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1534(line=44, offs=1) -- 1573(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1574(line=45, offs=1) -- 1616(line=45, offs=43)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_bsearch.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_quicksort.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayptr.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayref.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrix.dats: 1535(line=44, offs=1) -- 1574(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixptr.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixref.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1532(line=44, offs=1) -- 1566(line=44, offs=35)
*/
/*
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
typedef
struct {
atstkind_t0ype(atstype_int) atslab$x1; 
atstkind_t0ype(atstype_int) atslab$x2; 
} postiats_tyrec_0 ;
typedef
struct {
atstkind_t0ype(atstype_int) atslab$x1; 
postiats_tyrec_0 atslab$x2; 
} postiats_tyrec_1 ;
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
ATSstaticdec()
atsvoid_t0ype
foo_0() ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$2(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$3(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 129(line=10, offs=5) -- 377(line=22, offs=2)
*/
/*
local: 
global: foo_0$0(0)
local: 
global: 
*/
ATSstaticdec()
atsvoid_t0ype
foo_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret0, atsvoid_t0ype) ;
ATStmpdec(tmpref1, postiats_tyrec_1) ;
ATStmpdec(tmp2, postiats_tyrec_0) ;
ATStmpdec(tmp3, postiats_tyrec_0) ;
ATStmpdec_void(tmp4, atsvoid_t0ype) ;
ATStmpdec(tmp5, atstkind_t0ype(atstype_bool)) ;
ATStmpdec_void(tmp10, atsvoid_t0ype) ;
ATStmpdec(tmp11, atstkind_t0ype(atstype_bool)) ;
ATStmpdec_void(tmp14, atsvoid_t0ype) ;
ATStmpdec(tmp15, atstkind_t0ype(atstype_bool)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo_0:
/*
letpush(beg)
*/
/*
ATSINStmpdec(tmpref1)) ;
*/
ATSINSstore_fltrec_ofs (tmp2, postiats_tyrec_0, atslab$x1, ATSPMVi0nt(2)) ;
ATSINSstore_fltrec_ofs (tmp2, postiats_tyrec_0, atslab$x2, ATSPMVi0nt(3)) ;
ATSINSstore_fltrec_ofs (tmpref1, postiats_tyrec_1, atslab$x1, ATSPMVi0nt(1)) ;
ATSINSstore_fltrec_ofs (tmpref1, postiats_tyrec_1, atslab$x2, tmp2) ;
ATSINSstore(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x1), ATSPMVi0nt(11)) ;
ATSINSstore(ATSselfltrec(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x1), ATSPMVi0nt(22)) ;
ATSINSstore_fltrec_ofs (tmp3, postiats_tyrec_0, atslab$x1, ATSPMVi0nt(22)) ;
ATSINSstore_fltrec_ofs (tmp3, postiats_tyrec_0, atslab$x2, ATSPMVi0nt(33)) ;
ATSINSstore(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x2), tmp3) ;
ATSINSmove(tmp5, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$1(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x1), ATSPMVi0nt(11))) ;
ATSINSmove_void(tmp4, atspre_assert_errmsg_bool1(tmp5, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 280(line=16, offs=10) -- 301(line=16, offs=31)"))) ;
ATSINSmove(tmp11, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$2(ATSselfltrec(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x1), ATSPMVi0nt(22))) ;
ATSINSmove_void(tmp10, atspre_assert_errmsg_bool1(tmp11, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 312(line=17, offs=10) -- 336(line=17, offs=34)"))) ;
ATSINSmove(tmp15, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$3(ATSselfltrec(ATSselfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x2), ATSPMVi0nt(33))) ;
ATSINSmove_void(tmp14, atspre_assert_errmsg_bool1(tmp15, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 347(line=18, offs=10) -- 371(line=18, offs=34)"))) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret0, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret0) ;
} /* end of [foo_0] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$0(0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(2954)
tmparg = S2Evar(tk(2954))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret6, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7, PMVtmpltcst(g1int2int<S2EVar(406->S2Eextkind(atstype_int)), S2EVar(407->S2Evar(tk(2954)))>)(arg1)) ;
ATSINSmove(tmpret6, PMVtmpltcst(g1int_eq<S2Evar(tk(2954))>)(arg0, tmp7)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret6) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$1(1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(2954)
tmparg = S2Evar(tk(2954))
tmpsub = Some(tk(2954) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$1, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$1, atspre_g1int_eq_int(arg0, tmp7$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$1) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$2(1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(2954)
tmparg = S2Evar(tk(2954))
tmpsub = Some(tk(2954) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$2(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$2, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$2, atspre_g1int_eq_int(arg0, tmp7$2)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$2) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$2] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27035(line=704, offs=14) -- 27075(line=704, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$3(1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(2954)
tmparg = S2Evar(tk(2954))
tmpsub = Some(tk(2954) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$3(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$3, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$3, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$3, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$3, atspre_g1int_eq_int(arg0, tmp7$3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$3) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$ATS_055$Postiats_057$prelude_057$SATS_057$integer_056$sats_eq_g1int_int$1$3] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 411(line=24, offs=17) -- 422(line=24, offs=28)
*/
/*
local: foo_0$0(0)
global: foo_0$0(0), mainats_void_0$7$0(0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret18, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
ATSINSmove_void(tmpret18, foo_0()) ;
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret18) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */