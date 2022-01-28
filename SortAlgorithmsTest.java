import java.util.*;

class evaluacionSortings
{
   public static void main(String[] args) 
   {
   
     //input cliente
     java.util.Scanner input = new java.util.Scanner(System.in);
     System.out.println("Numero corridas simulacion: ");
     int numeroCorridas = input.nextInt();     
     System.out.println("Tamanio arreglo (inicial): ");
     int tamañoArreglo = input.nextInt();
     
     for (int p = 0; p < 5 ; p++)
     {
        //arreglos resultados Insertion
        ArrayList <Integer> ArregloComparacionesInsertion = new ArrayList <> ();
        ArrayList <Integer> ArregloIntercambiosInsertion = new ArrayList <> ();
        
        //arreglos resultados Bubble
        ArrayList <Integer> ArregloComparacionesBubble = new ArrayList <> ();
        ArrayList <Integer> ArregloIntercambiosBubble = new ArrayList <> ();
        
        //arreglos resultados Merge
        ArrayList <Integer> ArregloComparacionesMerge = new ArrayList <> ();
        ArrayList <Integer> ArregloIntercambiosMerge = new ArrayList <> ();
        
        //arreglos resultados Quick
        ArrayList <Integer> ArregloComparacionesQuick = new ArrayList <> ();
        ArrayList <Integer> ArregloIntercambiosQuick = new ArrayList <> ();
        
        //arreglos resultados Shell
        ArrayList <Integer> ArregloComparacionesShell = new ArrayList <> ();
        ArrayList <Integer> ArregloIntercambiosShell = new ArrayList <> ();
   
   
   
   
        //loop corridas
        for (int j = 0; j < numeroCorridas; j++)
        {
            //inisalizacion y relleno arreglo
            int[] list = new int [tamañoArreglo]; 
            for (int k=0; k < tamañoArreglo; k++)
            {list [k] = (int)(Math.random()*1000);}
            
            //copia del arreglo
            int[] copiaList = copiarArray(list);
            int[] MergeList = copiarArray(list);
            int[] QuickList = copiarArray(list);
            int[] ShellList = copiarArray(list);

            
            
            //Algoritmo Insertion
            int comparacionesInsertion = 0;
            int intercambiosInsertion = 0;
            
            for (int i = 1; i < list.length; i++) 
            {
               int currentElement = list[i];
               int k;
               
               for (k = i - 1, comparacionesInsertion++; k >= 0 && list[k] > currentElement ; k--)
               {
                
                  list[k + 1] = list[k];
                  intercambiosInsertion++;
               }
               
               list[k + 1] = currentElement;
            }
                     
            ArregloComparacionesInsertion.add(comparacionesInsertion);
            ArregloIntercambiosInsertion.add(intercambiosInsertion);
            
      
            
            //Algoritmo Bubble
            int comparacionesBubble = 0;
            int intercambiosBubble = 0;
            boolean needNextPass = true;
          
            for (int k = 1; k < copiaList.length && needNextPass; k++) 
            {
                needNextPass = false;
                
                for (int i = 0; i < copiaList.length - k; i++)
                {
                   comparacionesBubble++;
                   
                   if (copiaList[i] > copiaList[i + 1]) 
                   {
                      int temp = copiaList[i];
                      copiaList[i] = copiaList[i + 1];
                      copiaList[i + 1] = temp;
                      intercambiosBubble++;
                      needNextPass = true;
                   }
                }
            }
            
            ArregloComparacionesBubble.add(comparacionesBubble);
            ArregloIntercambiosBubble.add(intercambiosBubble);
            
            
            //Algoritmo Merge
            int [] comparacionesIntercambiosMerge = mergeSort(MergeList); 
            
            ArregloComparacionesMerge.add(comparacionesIntercambiosMerge[0]);
            ArregloIntercambiosMerge.add(comparacionesIntercambiosMerge[1]);
   
            //Algoritmo QuickSort
            int [] comparacionesIntercambiosQuick = quickSort(QuickList); 
            
            ArregloComparacionesQuick.add(comparacionesIntercambiosQuick[1]);
            ArregloIntercambiosQuick.add(comparacionesIntercambiosQuick[2]); 
            
            //Algoritmo ShellSort
            int [] comparacionesIntercambiosShell = shellSort(ShellList); 
            
            ArregloComparacionesShell.add(comparacionesIntercambiosShell[0]);
            ArregloIntercambiosShell.add(comparacionesIntercambiosShell[1]); 

              
            
         }//loop corridas
         
         //impresion resultados
         final Object[][] table = new String[6][];
         table[0] = new String[] { "n = " + tamañoArreglo ,   "Comparaciones", "Intercambios" };
         table[1] = new String[] { "Insercion",promedio(ArregloComparacionesInsertion), promedio(ArregloIntercambiosInsertion) };
         table[2] = new String[] { "Bubble", promedio(ArregloComparacionesBubble), promedio(ArregloIntercambiosBubble) };
         table[3] = new String[] { "Merge", promedio(ArregloComparacionesMerge), promedio(ArregloIntercambiosMerge) };
         table[4] = new String[] { "Quick", promedio(ArregloComparacionesQuick), promedio(ArregloIntercambiosQuick) };
         table[5] = new String[] { "Shell", promedio(ArregloComparacionesShell), promedio(ArregloIntercambiosShell) };
   
   
         for (final Object[] row : table) 
         {
            System.out.format("%15s%15s%15s\n", row);
         } 
         
         System.out.println("---------------------------------------------");
         
         tamañoArreglo = tamañoArreglo *2;
      
            
      }//loop n's
      
      
   }//main
   
 
 //varios
  public static int[] copiarArray(int[] list) {
  
     int [] copia = new int [list.length];
     for(int i=0; i<list.length; i++)
     {copia [i] = list[i];}
     
   
     return copia; 
  }
  
  public static String promedio (ArrayList <Integer> arreglo)
  {
   float suma = 0;
   
   for (Integer e: arreglo)
   {suma= suma + e;}
   
   String promedio = String.valueOf(suma/arreglo.size());
  
  
   return promedio;
  }
  
  //MergeSort
  
  public static int [] mergeSort(int[] list)
  {
    if (list.length > 1) 
    {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      return merge(firstHalf, secondHalf, list);
    }
    
    else return new int [2];
  }
  
  public static int [] merge(int[] list1, int[] list2, int[] temp) 
  {
    int comparacionesMerge = 3;
    int intercambiosMerge = 0;
    int [] coparacionesIntercambios = new int [2];
    
    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    while (current1 < list1.length && current2 < list2.length) 
    {
      comparacionesMerge++;
      
      if (list1[current1] < list2[current2])
        {
            temp[current3++] = list1[current1++];
            intercambiosMerge++;
        }
        
      else
        {
            temp[current3++] = list2[current2++];
            intercambiosMerge++;            
        }
        
        comparacionesMerge++;
    }

    while (current1 < list1.length)
      {
         comparacionesMerge++;
         temp[current3++] = list1[current1++];
         intercambiosMerge++;
      }

    while (current2 < list2.length)
    {
      comparacionesMerge++;
      temp[current3++] = list2[current2++];
      intercambiosMerge++;
    }
    
    coparacionesIntercambios [0] = comparacionesMerge;
    coparacionesIntercambios [1] = intercambiosMerge;
    
     return coparacionesIntercambios;
  }
  
  //metodos quick sort
  
  public static int [] quickSort(int[] list)
  {
    return quickSort(list, 0, list.length - 1);
  }

  private static int [] quickSort(int[] list, int first, int last)
   {
    if (last > first) 
    {
      int [] arregloInfo = partition(list, first, last);
      int pivotIndex = arregloInfo [0];
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
      
      return arregloInfo;
    }
    
    return new int [3];
  }
  
  /** Partition the array list[first..last] */
  private static int [] partition(int[] list, int first, int last) 
  {
    //variables instrumentacion
    int comparacionesQuick = 9;
    int intercambiosQuick = 0;
    int [] arregloInfo2 = new int [3];
  
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low)
    {
      // Search forward from left
      while (low <= high && list[low] <= pivot)
        {
         low++;
         comparacionesQuick = comparacionesQuick + 2;
        }

      // Search backward from right
      while (low <= high && list[high] > pivot)
       {
         high--;
         comparacionesQuick = comparacionesQuick + 2;
       }

      // Swap two elements in the list
      if (high > low) 
      {
        comparacionesQuick++;
        
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
        
        intercambiosQuick++;
      }
    }

    while (high > first && list[high] >= pivot)
      {
         high--;
         comparacionesQuick = comparacionesQuick + 2;
      }

    // Swap pivot with list[high]
    if (pivot > list[high])
    {
      comparacionesQuick++;
      
      list[first] = list[high];
      list[high] = pivot;
      
      intercambiosQuick++;
      
      arregloInfo2 [0] = high;
      arregloInfo2 [1] = comparacionesQuick;
      arregloInfo2 [2] = intercambiosQuick;
      
      return arregloInfo2;
    }
    else
    {
      comparacionesQuick++;
      
      arregloInfo2 [0] = first;
      arregloInfo2 [1] = comparacionesQuick;
      arregloInfo2 [2] = intercambiosQuick;

      return arregloInfo2;
    }
    
  }//Partition
  
  //ShellSort 
  
  public static int[] shellSort(int arr[]) 
    { 
        int[] arregloInfo  = new int [2];
        int n = arr.length; 
        int comparaciones = 0;
        int intercambios = 0;
  
        // Start with a big gap, then reduce the gap 
        for (int gap = n/2 ; gap > 0; gap /= 2, comparaciones++) 
        { 
            // Do a gapped insertion sort for this gap size. 
            // The first gap elements a[0..gap-1] are already 
            // in gapped order keep adding one more element 
            // until the entire array is gap sorted 
            for (int i = gap; i < n; i += 1, comparaciones++) 
            { 
                // add a[i] to the elements that have been gap 
                // sorted save a[i] in temp and make a hole at 
                // position i 
                int temp = arr[i]; 
                intercambios++;
  
                // shift earlier gap-sorted elements up until 
                // the correct location for a[i] is found 
                int j; 
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap, comparaciones++) 
                    {
                       arr[j] = arr[j - gap];
                       intercambios++;
                    } 
  
                // put temp (the original a[i]) in its correct 
                // location 
                arr[j] = temp; 
                intercambios++;
            } 
        } 
        arregloInfo [0]= comparaciones;
        arregloInfo [1]= intercambios;
        
        return arregloInfo; 
    } 
  
   
}