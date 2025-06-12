.data
suma:   .double 0.0   # Resultado acumulado
tres:   .double 3.0
cuatro: .double 4.0
cinco:  .double 5.0
dos:    .double 2.0
tres_n: .double 3.0   # Para numerador (2n + 3)

.text
main:
    daddi r1, r0, 1         # r1 = n = 1
    daddi r20, r0, suma     # r20 = dirección de 'suma'

loop:
    daddi r2, r1, 0         # r2 = n

    # ---------- Numerador: (2n + 3) ----------
    daddi r3, r2, 0         # r3 = n
    daddi r4, r0, 2
    dmulu r5, r3, r4        # r5 = 2 * n
    daddi r6, r5, 3         # r6 = 2n + 3
    mtc1 r6, f1
    cvt.d.l f1, f1          # f1 = double(2n + 3)

    # ---------- Denominador: 3n^3 + 4n + 5 ----------
    dmul r7, r2, r2         # r7 = n^2
    dmul r8, r7, r2         # r8 = n^3
    daddi r9, r0, 3
    dmulu r10, r8, r9       # r10 = 3 * n^3

    daddi r11, r0, 4
    dmulu r12, r2, r11      # r12 = 4 * n

    dadd r13, r10, r12      # r13 = 3n^3 + 4n
    daddi r13, r13, 5       # r13 = 3n^3 + 4n + 5
    mtc1 r13, f2
    cvt.d.l f2, f2          # f2 = double(denominador)

    # ---------- División y acumulación ----------
    div.d f3, f1, f2        # f3 = numerador / denominador
    l.d f4, 0(r20)          # cargar desde la dirección de suma
    add.d f4, f4, f3        # f4 += fracción
    s.d f4, 0(r20)          # guardar en la dirección de suma

    # ---------- Incrementar n ----------
    daddi r1, r1, 1         # n++

    bne r1, r14, loop       # repetir mientras n < 6
    daddi r14, r0, 6        # delay slot (seguro de mover aquí)

    halt
