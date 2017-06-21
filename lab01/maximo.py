# coding: utf-8

def maxElement(array):
	maior = array[0]
	for j in array:
		if j > maior:
			maior = j

	return "Máximo: %i" %maior
	
def minElement(array) :
	menor = array[0]
	for i in array:
		if i < menor:
			menor = i
			
	return "Mínimo: %i" %menor
