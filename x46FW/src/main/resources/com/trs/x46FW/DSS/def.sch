pri_loop_work:
	get_vcall_cd d0
	pri_get r0, gn5
	cmp gn5, cpn
	doeq:
		push_vet d0
	doneq:
		rep
	ret_done
	rep
; h


main:
	mov cpn, mpn

	mov d1, vet[4]

	mov gn4, vet_size

	push_vet vet[gn4]

    loop:
    dec cpn

    thr_mk pri_loop_work


    cmp cpn, 0
    jeq end_loop
    jmp loop
end_loop:
	irp_ret.w