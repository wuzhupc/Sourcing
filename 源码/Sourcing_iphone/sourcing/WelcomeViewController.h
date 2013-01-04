//
//  WelcomeViewController.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseServiceDelegate.h"

#define CINT_TAG_CHECKUSER_LOGIN 10001

@interface WelcomeViewController : UIViewController<BaseServiceDelegate>
@property (weak, nonatomic) IBOutlet UILabel *laVersion;
@property (weak, nonatomic) IBOutlet UIImageView *ivWelcome;

@end
