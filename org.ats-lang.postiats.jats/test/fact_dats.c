/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-1-24: 22h:15m
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
#endif /* _ATS_CCOMP_HEADER_NONE */


/*
** include prelude cats files
*/
#ifndef _ATS_CCOMP_PRELUDE_NONE
//
#include "prelude/CATS/basics.cats"
#include "prelude/CATS/integer.cats"
#include "prelude/CATS/pointer.cats"
#include "prelude/CATS/bool.cats"
#include "prelude/CATS/char.cats"
#include "prelude/CATS/string.cats"
#include "prelude/CATS/float.cats"
//
#include "prelude/CATS/list.cats"
#include "prelude/CATS/option.cats"
#include "prelude/CATS/array.cats"
#include "prelude/CATS/arrayptr.cats"
#include "prelude/CATS/arrayref.cats"
#include "prelude/CATS/matrix.cats"
//
#endif /* _ATS_CCOMP_PRELUDE_NONE */

/*
typedefs-for-tyrecs-and-tysums(beg)
*/
/*
typedefs-for-tyrecs-and-tysums(end)
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
fact_0 (atstkind_t0ype(atstype_int) arg0) ;

#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2762)))) arg0, atstkind_t0ype(atstype_int) arg1) ;
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1) ;

ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats () ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
fact_0 (atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp6, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp8, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp9, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_fact_0:
/*
letpush(beg)
*/
/*
letpush(end)
*/

// tmp1 = xxx(arg0, 0)
ATSINSmove(tmp1, _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1(arg0, ATSPMVi0nt(0))) ;
ATSif(tmp1) ATSthen() {
/*
letpush(beg)
*/
ATSINSmove(tmp7, atspre_g1int_sub_int(arg0, ATSPMVi0nt(1))) ;
ATSINSmove(tmp6, fact_0(tmp7)) ;
ATSINSmove(tmp8, ATSselrecsin(tmp6, atstkind_t0ype(atstype_int), atslab$1)) ; 
/* (*nothing*) */
ATSINSmove(tmp9, atspre_g1int_mul_int(arg0, tmp8)) ;
/*
letpush(end)
*/

ATSINSmove(tmp0, tmp9) ;
/*
INSletpop()
*/
} ATSelse() {
ATSINSmove(tmp0, ATSPMVi0nt(1)) ;
} /* end of [if] */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp0) ;
} /* end of [fact_0] */
#if(0)
ATSglobaldec()
/*
imparg = tk(2762)
tmparg = S2Evar(tk(2762))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2762)))) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3, atstkind_t0ype(postiats_undef(HSEtyvar(tk(2762))))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g1int_int:
ATSINSmove(tmp3, PMVtmpltcst(g1int2int<S2EVar(393->S2Etkname(atstype_int)), S2EVar(394->S2Evar(tk(2762)))>)(arg1)) ;
ATSINSmove(tmp2, PMVtmpltcst(g1int_gt<S2Evar(tk(2762))>)(arg0, tmp3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp2) ;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1] */
#endif // end of [TEMPLATE]
ATSstaticdec()
/*
imparg = tk(2762)
tmparg = S2Evar(tk(2762))
tmpsub = Some(tk(2762) -> S2Etkname(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2$1, atstkind_t0ype(atstype_bool)) ;  // bool tmp2$1
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_int)) ;  // int tmp3$1
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g1int_int:
ATSINSmove(tmp3$1, atspre_g1int2int_int_int(arg1)) ;  // tmp3$1 = (int)arg1;
ATSINSmove(tmp2$1, atspre_g1int_gt_int(arg0, tmp3$1)) ;  // tmp2$1 = atspre_g0int_gt_int (arg0, tmp3$1);
/* funbodyinstrlst(end) */
ATSreturn(tmp2$1) ;  // return tmp2$1;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1] */
ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats ()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp10, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp11, atsvoid_t0ype) ;
ATStmpdec_void(tmp12, atsvoid_t0ype) ;
ATStmpdec_void(tmp13, atsvoid_t0ype) ;
ATStmpdec_void(tmp14, atsvoid_t0ype) ;
ATStmpdec(tmp15, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp16, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp17, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void:
/*
letpush(beg)
*/
/* (*nothing*) */
ATSINSmove_void(tmp11, atspre_print_string(ATSPMVstring("fact("))) ;
ATSINSmove_void(tmp12, atspre_print_int(ATSPMVi0nt(12))) ;
ATSINSmove_void(tmp13, atspre_print_string(ATSPMVstring(") = "))) ;
ATSINSmove(tmp16, fact_0(ATSPMVi0nt(12))) ;
ATSINSmove(tmp15, ATSselrecsin(tmp16, atstkind_t0ype(atstype_int), atslab$1)) ; 
ATSINSmove_void(tmp14, atspre_print_int(tmp15)) ;
ATSINSmove_void(tmp17, atspre_print_newline()) ;
/*
letpush(end)
*/

ATSINSmove(tmp10, ATSPMVi0nt(0)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp10) ;
} /* end of [mainats] */
/*
** declaration initialization
*/