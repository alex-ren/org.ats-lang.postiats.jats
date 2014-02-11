/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-12-25:  0h:26m
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
#ifndef _ATS_EXCEPTION_NONE
#include "pats_ccomp_memalloca.h"
#include "pats_ccomp_exception.h"
#endif // end of [_ATS_EXCEPTION_NONE]
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
#include "prelude/CATS/integer_ptr.cats"
#include "prelude/CATS/integer_fixed.cats"
#include "prelude/CATS/float.cats"
#include "prelude/CATS/memory.cats"
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
ATSdyncst_extfun(_057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_sys_model_056_sats__sys_gvar_create, (atstkind_t0ype(atstype_int)), global_variable_t) ;
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
#ifndef _ATS_EXCEPTION_NONE
extern void the_atsexncon_initize (atstype_exncon *d2c, char *exnmsg) ;
#endif // end of [_ATS_EXCEPTION_NONE]
/*
exnconlst-declaration(end)
*/
/*
assumelst-declaration(beg)
*/
/*
assumelst-declaration(end)
*/
/*
extypelst-declaration(beg)
*/
/*
extypelst-declaration(end)
*/
ATSstatmpdec(statmp0, global_variable_t) ;
ATSstatmpdec(statmp1, atstkind_t0ype(atstype_int)) ;
ATSstatmpdec(statmp2, atstkind_t0ype(atstype_int)) ;
/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_01_global_variable_056_dats__dynload()
{
ATSdynload1(
_057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_01_global_variable_056_dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_01_global_variable_056_dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_01_global_variable_056_dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
/*
emit_instr: loc0 = /home/alex/workspace/Postiats/projects/utfpl/test/src_ats/01_global_variable.dats: 42(line=4, offs=13) -- 60(line=4, offs=31)
*/
ATSINSmove(statmp0, _057_home_057_alex_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_src_ats_057_sys_model_056_sats__sys_gvar_create(ATSPMVi0nt(2))) ;

/*
emit_instr: loc0 = /home/alex/workspace/Postiats/projects/utfpl/test/src_ats/01_global_variable.dats: 75(line=6, offs=13) -- 99(line=6, offs=37)
*/
ATSINSmove(statmp2, PMVtmpltcstmat[0](sys_gvar_get<S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))>)(statmp0)) ;

/*
emit_instr: loc0 = /home/alex/workspace/Postiats/projects/utfpl/test/src_ats/01_global_variable.dats: 71(line=6, offs=9) -- 99(line=6, offs=37)
*/
ATSINSmove(statmp1, PMVtmpltcstmat[0](g0int_add<S2Eextkind(atstype_int)>)(ATSPMVi0nt(3), statmp2)) ;

} /* ATSendif */
ATSreturn_void() ;
} /* end of [*_dynload] */