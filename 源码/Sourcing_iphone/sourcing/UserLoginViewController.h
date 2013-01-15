//
//  UserLoginViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <UIKit/UIKit.h>
#import "RadiusView.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface UserLoginViewController : UIViewController


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (weak, nonatomic) IBOutlet UILabel *labelTitle;
@property (weak, nonatomic) IBOutlet RadiusView *viewInput;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Outlets

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
- (IBAction)actionReturn:(id)sender;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions


@end
