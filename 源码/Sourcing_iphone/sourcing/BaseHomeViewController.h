//
//  BaseHomeViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <UIKit/UIKit.h>
#import "ChannelVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface BaseHomeViewController : UIViewController
{
    ChannelVO *_fatherchannel;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property ChannelVO *fatherchannel;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Outlets
@property (weak, nonatomic) IBOutlet UINavigationBar *customNavigationBar;
@property (weak, nonatomic) IBOutlet UINavigationItem *titleNavigationItem;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(void)setNavTitle:(NSString *)ktitle;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions


@end
