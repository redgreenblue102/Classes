import System.IO
import Data.Char
numToIpAddress :: Int -> String
numToIpAddress num = numToIpAddressHelper num 32 8 0

numToIpAddressHelper :: Int -> Int -> Int -> Int-> String
numToIpAddressHelper num currBit 0 currCycleNum
    |currBit /= 0 = show currCycleNum ++ "." ++ numToIpAddressHelper num currBit 8 0
    |currBit == 0 = show currCycleNum 
numToIpAddressHelper num currBit currCycleBit currCycleNum
    |num >= value = numToIpAddressHelper (num-value) (currBit-1) cyclePow (currCycleNum+2^(cyclePow))
    |num < value = numToIpAddressHelper num (currBit-1) cyclePow currCycleNum
    where value = 2^(currBit-1)
          cyclePow = currCycleBit-1
          
ipAddressToNum :: [Char] -> Int
ipAddressToNum ip = ipAddressToNumHelper ip "" 32

ipAddressToNumHelper :: [Char] -> String -> Int -> Int
ipAddressToNumHelper [] segment bit = numBinaryShifted (read segment) bit 8
ipAddressToNumHelper _ _ 0 = 0
ipAddressToNumHelper (x:xs) segment bit
    |x == '.' = (numBinaryShifted (read segment) bit 8) + (ipAddressToNumHelper xs "" (bit-8))
    |x /= '.' = ipAddressToNumHelper xs (segment ++ [x]) bit

numBinaryShifted :: Int -> Int -> Int -> Int
numBinaryShifted _ _ 0 = 0
numBinaryShifted num bitShift numBit
    |num >=value = 2^(bitShift-1) + numBinaryShifted (num-value) (bitShift-1) (numBit-1)
    |num < value = numBinaryShifted num (bitShift-1) (numBit-1)
    where value = 2^(numBit-1)


    
randomList = [14, 25, 23, 12, 11, 17, 26, 1, 3, 13, 2, 4, 21, 22, 16, 18, 24, 9, 19, 20, 6, 5, 7, 10, 15, 8]

generateKey :: String
generateKey = generateKeyHelper randomList 26

generateKeyHelper :: [Int] -> Int -> String 
generateKeyHelper _ 0 = ""
generateKeyHelper selectable left = (chr (65-1 + head selectable)):generateKeyHelper (drop 1 selectable) (left-1)


--for Both Upper and Lower Case
encrypt :: [Char] -> [Char]-> String
encrypt [] _ = ""
encrypt (letter:restOfplain) key 
    |(ord letter <=(65+25)) && (ord letter >= 65) = [(getIndex key 0 ((ord letter)-65))] ++ encrypt restOfplain key
    |(ord letter <=(97+25)) && (ord letter >= 97) =[(getIndex key 0 ((ord letter)-97))] ++ encrypt restOfplain key
    |letter == ' ' = " " ++ encrypt restOfplain key
    |otherwise = [letter] ++ encrypt restOfplain key
    
--for UpperCase Only
{-
encrypt :: [Char] -> [Char]-> String
encrypt [] _ = ""
encrypt (letter:restOfplain) key 
    |(ord letter <=(65+25)) && (ord letter >= 65) = [(getIndex key 0 ((ord letter)-65))] ++ encrypt restOfplain key
    |letter == ' ' = " " ++ encrypt restOfplain key
    |otherwise = [letter] ++ encrypt restOfplain key
-}
getIndex :: [Char] -> Int -> Int -> Char
getIndex (x:xs) currIndex targetIndex
    |currIndex < targetIndex = getIndex xs (currIndex+1) targetIndex
    |currIndex == targetIndex = x

decrypt :: [Char] -> [Char] ->String
decrypt [] _ = ""
decrypt (letter:restOfplain) key 
    |(ord letter <=(65+25)) && (ord letter >= 65) = [chr (65+(getIndexOfContent key (ord letter)) 0)] ++ decrypt restOfplain key
    |(ord letter <=(97+25)) && (ord letter >= 97) =[chr (65+(getIndexOfContent key ((ord letter)-32) 0))] ++ decrypt restOfplain key
    |letter == ' ' = " " ++ decrypt restOfplain key
    |otherwise = [letter] ++ decrypt restOfplain key
    
getIndexOfContent :: [Char] -> Int -> Int -> Int
getIndexOfContent [] find _ = find 
getIndexOfContent (key:rest) find currIndex
    |find == (ord key) = currIndex
    |otherwise = getIndexOfContent rest find (currIndex+1)

--usage below
num = 2130706433
--num = 1965829182
--num = 3914761821


ipAddress = numToIpAddress num
ipBackToNum = ipAddressToNum ipAddress



key = generateKey
plainText = "My Name Is Bob."
encryptedText = encrypt plainText key
decryptedText = decrypt encryptedText key

main :: IO ()
main = do
    putStrLn $ "The ip of " ++ (show num) ++ " is " ++ show ipAddress
    putStrLn $ "Turning it back we have " ++ show ipBackToNum
    putStrLn $ "Our key is " ++ show key
    putStrLn $ "Our plain text is " ++ show plainText
    putStrLn $ "Encrypted it becomes " ++ show encryptedText
    putStrLn $ "Decrypting it then becomes " ++ show decryptedText
    
    
    
    
    