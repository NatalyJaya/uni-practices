
import System.Environment (getArgs)
import Data.List (nub, delete, insert)
import Data.Maybe (fromMaybe)

type Regla = (String, String, String)

-- Parseo de entrada
parsejarEntrada :: String -> [Regla]
parsejarEntrada = map ((\[a, b, c] -> (a, b, c)) . words) . lines

-- Posición de un elemento
index :: Eq a => a -> [a] -> Maybe Int
index x xs = lookup x (zip xs [0..])

-- Comprobar si una regla se cumple
comprovarRegla :: [String] -> Regla -> Bool
comprovarRegla fila (a, b, c) =
  case (index a fila, index b fila) of
    (Just i, Just j)
      | j > i -> case index c fila of
                   Just k -> k > i && k < j
                   Nothing -> False
      | otherwise -> True  -- enemigo delante o misma posición
    _ -> True  -- alguno no está en la fila → no se verifica aún

-- Validar fila completa
esFilaValida :: [String] -> [Regla] -> Bool
esFilaValida fila regles = all (comprovarRegla fila) regles

-- Construcción progresiva con backtracking
inserir :: [String] -> [Regla] -> [[String]]
inserir [] _ = [[]]
inserir (x:xs) regles = do
  filaParcial <- inserir xs regles
  pos <- [0 .. length filaParcial]
  let novaFila = insertAt pos x filaParcial
  if esFilaValida novaFila regles
    then return novaFila
    else []

insertAt :: Int -> a -> [a] -> [a]
insertAt i x xs = let (a, b) = splitAt i xs in a ++ [x] ++ b

-- Obtener el ordre de fatxenderia correcte
ordreFatxenderia :: [Regla] -> [String]
ordreFatxenderia regles =
  let fatxendes = nub [a | (a, _, _) <- regles]
      restants  = nub [x | (a, b, c) <- regles, x <- [b, c], x `notElem` fatxendes]
  in fatxendes ++ restants

--Validar que ninguna regla sea absurda (nadie se odia a sí mismo)
reglesValides :: [Regla] -> Bool
reglesValides = all (\(a, b, _) -> a /= b)

-- Función principal
resoldre :: [Regla] -> String
resoldre regles =
  case inserir (ordreFatxenderia regles) regles of
    (fila:_) -> unwords fila
    [] -> "impossible"

-- Main
main :: IO ()
main = do
  args <- System.Environment.getArgs
  (entrada, sortida) <- case args of
    [inp, out] -> return (inp, out)
    _ -> error "Ús: ./illa <fitxer_entrada> <fitxer_sortida>"

  contingut <- readFile entrada
  let regles = parsejarEntrada contingut

  -- Verifica si hay reglas inválidas como "R R F"
  if not (reglesValides regles)
    then writeFile sortida "impossible\n"
    else writeFile sortida (resoldre regles ++ "\n")