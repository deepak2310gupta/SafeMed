# SafeMed
This is a android app to prevent users from using counterfiet medcines and also to contact fake dealers for essentials.

## Description
  ### Problem Statement
    In the present time, essentials like viral drugs, oxygen contrators etc. are in very high demand.There this shortage of these essentias in legal market because of less production in comparison to demand. So, desparate families of covid patients are approaching black market to buy the above mentioned essentials.Buying essentials from these market can cause major problems:

      1. Buyer can get a counterfiet medicine.
      2. Buyer can get a medicine not approved by government.
      2. Buyer can be duped by a dealer making fake promises of providing essentials.

  ### Proposed Solution

  #### Problem 1 Solution
    
    We have observed that counterfiet medcines have some dissimilarities in their packaging style as comapred original medicine packet.For example, gaping between letters , colour sharpnes , thickness of letters etc.These differences are so minimal that it is not observable to buyer.
    
    To detect these dissimilarities between original and counterfiet packaging, we have introduced a deeplearning model based on siamese network.The models encodes a image into a vector of 256 elements.The model is trained in such a way that euclidean distance between encodings of images of same medcines is very less while between images of different medicines is large.
    On experementation we have observed that distance between image of same medicines is around less than 0.3 at an average.So, we have set *0.3* as threshold for our verification of medicines.
    
  #### Problem 2 solution
  
     We are using same model to detect fake medicines . Here we are showing an example of the solution:
     Remdesevir drug is manufactured by to two companies (as approved by government).In market there are total three type of packets of remdesivir in legal market.So, we have calculated the embeddings of images of three different type of original packaging.Now, when a users inputs a image,it is encoded by the model and the euclidean distance between the users image and original images is calculated
     If all the distances is greater than threshold, then we output the user medicine as fake one.
     
     ##### Training of Model
       We have used 180 different classes of for training and 19 classes for validation.
        *We have obtained the dataset thorough google images.
        
        *One image per class has been obtained through google.
        
        *Dataset includes such images  that for each image, there are such images in dataset that looks very similar to it but have different class which was useful for making model train hard.
        
        *Image per classes is made 30 by using data augmentation.Augmentation operation includes changing brightness,shear,rotation,shifting.Images are augmented very slightly in such a way that they look like real image with some changes occured during taking their images though camera in general.
        
        *Augmenation is not applied on validation data.
        
        *We have used Google *Exception* pretrained model trained on Imagenet Dataset.
        
        *SemiHardTriplet Loss is used as a metric for optimization during training of model.
        
        
        *Result with above mentioned dataset:
         * *Training Accuracy* - 82%
         * *Validation Accuracy* - 80%
         
         ###### Example of working model:
         
         
         
        
        
        
