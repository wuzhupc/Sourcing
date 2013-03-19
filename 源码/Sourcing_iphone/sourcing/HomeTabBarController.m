//
//  HomeTabBarController.m
//  sourcing
//
//  Created by wuzhu on 13-1-2.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "HomeTabBarController.h"
#import "ChannelVO.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "BaseHomeViewController.h"


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface HomeTabBarController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation HomeTabBarController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitHomeTabBarController
{
    self.tabBar.tintColor = [UIColor clearColor];
    self.tabBar.backgroundColor = [[UIColor alloc] initWithRed:96.0/255 green:96.0/255 blue:96.0/255 alpha:0.5];
    //[self.tabBar setBackgroundImage:[UIImage imageNamed:@"tabbar_backgroundimage"]];
    //self.tabBar.selectionIndicatorImage = [UIImage imageNamed:@"tabbar_selectionindicatorimage"];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitHomeTabBarController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitHomeTabBarController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    [self setTabBarControllerTitle];
    
}

- (void)viewDidUnload {
	// your code here
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods
-(void) setTabBarControllerTitle
{
    if([self.viewControllers count]==0)
        return;
    NSArray *channels = [ChannelVO getFatherChannels:[[ApplicationSet shareData] channels]];
   if(channels==0||[channels count]==0)
       return;
    [self initHomeViewController:(BaseHomeViewController *)[self.viewControllers objectAtIndex:0] channel:[ChannelVO getChannel:channels type:TYPE_FATHER_NEWS]];
    [self initHomeViewController:(BaseHomeViewController *)[self.viewControllers objectAtIndex:1] channel:[ChannelVO getChannel:channels type:TYPE_FATHER_PERSON]];
    [self initHomeViewController:(BaseHomeViewController *)[self.viewControllers objectAtIndex:2] channel:[ChannelVO getChannel:channels type:TYPE_FATHER_USER]];
    [self initHomeViewController:(BaseHomeViewController *)[self.viewControllers objectAtIndex:3] channel:[ChannelVO getChannel:channels type:TYPE_FATHER_MORE]];
    
    //推送信息显示
    //debug
    //[[UIApplication sharedApplication] setApplicationIconBadgeNumber:3];
    UIViewController *viewController = [self.viewControllers objectAtIndex:2];
    if(viewController!=nil)
    {
    NSInteger number =  [[UIApplication sharedApplication] applicationIconBadgeNumber];
    if(number>0)
    {
        viewController.tabBarItem.badgeValue = [NSString stringWithFormat:@"%d", number];
    }
    else
        viewController.tabBarItem.badgeValue = nil;
    }
}

-(void)initHomeViewController:(BaseHomeViewController *)kbvc channel:(ChannelVO *)kvo
{
    if(kvo!=nil&&kbvc!=nil)
    {
        [kbvc setFatherchannel:kvo];
        [kbvc setNavTitle:kvo.channelName];
    }
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods


@end
