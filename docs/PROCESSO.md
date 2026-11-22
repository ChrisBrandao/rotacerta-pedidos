# Processo de trabalho — Rota Certa Logística / Time de Plataforma

## Fluxo de um ticket
1. Leia o ticket inteiro + regras de negócio relacionadas.
2. Dúvida de requisito? Pergunte **antes** de codar. Pergunta boa não é fraqueza; suposição errada é retrabalho.
3. Crie uma branch: `tipo/RC-XXX-descricao-curta` (ex.: `fix/RC-101-calculo-total`).
4. Commits no padrão Conventional Commits: `fix(pedido): considera quantidade no valor total (RC-101)`.
5. Abra o PR (ou mande o link do commit/diff) com o template abaixo.

## Template de PR
```
## Ticket
RC-XXX

## O que foi feito
-

## Como testar
-

## Testes adicionados
-

## Pontos de atenção / dúvidas
-
```

## Definition of Done
- [ ] `mvn test` passa
- [ ] Todo bug corrigido vem com teste que falhava antes e passa depois
- [ ] Sem código comentado, `System.out.println` ou import não usado
- [ ] Regras de `REGRAS_DE_NEGOCIO.md` respeitadas
- [ ] README atualizado se mudou endpoint

## Prazos
- Atraso **avisado antes** do prazo, com motivo: tranquilo, a gente replaneja.
- Atraso **sem aviso**: vira registro na avaliação.
- Entrega no prazo mas quebrando o build: pior que atraso.

## Avaliação e plano de carreira
| Nível | Critério para subir |
|---|---|
| Estagiário I | ponto de partida |
| Estagiário II | 2 sprints com ≥ 80% dos tickets aprovados sem retrabalho grande + review técnica |
| Estagiário III | assume ticket de feature sozinho, do requisito ao teste de integração |
| Dev Júnior I | entrevista de efetivação: desenho de solução + live coding |

Cada entrega recebe nota de 0 a 10 em: **correção, testes, legibilidade, aderência ao processo, comunicação**.
