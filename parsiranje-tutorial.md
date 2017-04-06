# Parsiranje
Opste stvari o parsiranju izraza.


## Notacije
- prefix: `+ 2 3; * 7 8; + 2 * 3 4;`
- infix: `2 + 3 * 7 + 8 - 2;`
- postfix: `2 3 + 4 5 +  *;

## Postfix parsiranje
Za parsiranje izraza u postfix notaciji koristi se **stack**. 
Algoritam: idemo kroz izraz i proveravamo svaki karakter, ako je **broj**
stavimo na stack, ako je **operator** onda skinemo prva dva elementa sa stack-a
izvrsimo operaciju i stavimo na stack, pa idemo dalje.
**Napomena**: kada se brojevi skidaju sa stack-a, skidaju se u obrnutom poretku, 
pa prvi broj posmatramo kao drugi a drugi kao prvi.

## Infix parsiranje
Ovde je najlakse prebaciti iz infix u postfix. 
Prioritet operatora: ^, *, /, +, -, ovo je bitno za kasnije.

`2 + 3 + 4` 

`2 3 + 4 +`

Algoritam: idemo kroz izraz k po k, ako je
karakter **broj** onda samo ispisemo, ako je **operator** stavimo ga na stack
i tu cuvamo sve dok se ne pojavi operator manjeg ili jednakog prioriteta. Kada se sledeci takav
operator pojavi mi trenutni pop-ujemo i ispisemo (stavimo u string). Kada dodjemo do kraja izbacimo 
sve sto je ostalo na stack-u i stavimo u string. Na kraju samo evaluiramo preko funckije za postfix.


## Prefix parsiranje
Prefix izrazi se lako parsiraju rekuzijom. 
Idemo kroz izraz kpk. Ako je operator pozovemo, a uvek ce prvi biti operator onda rekurzivno pozovemo funkciju na ostatak izraza.
Primer u python-y
```python
from collections import deque
def parse(tokens):
    token=tokens.popleft()
    if token=='+':
            return parse(tokens)+parse(tokens)
    elif token=='-':
            return parse(tokens)-parse(tokens)
    elif token=='*':
            return parse(tokens)*parse(tokens)
    elif token=='/':
            return parse(tokens)/parse(tokens)
    else:
            # must be just a number
            return int(token)


if __name__=='__main__':
        expression="+ 2 2"
        print parse(deque(expression.split()))
```
